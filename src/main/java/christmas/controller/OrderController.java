package christmas.controller;

import christmas.controller.dto.OrderBenefitPrice;
import christmas.controller.dto.OrderMenuDto;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.domain.event.item.GiftItem;
import christmas.domain.order.Order;
import christmas.service.OrderService;
import christmas.service.dto.DiscountInfoWithGiftItemDTO;
import christmas.util.ConvertOrderItem;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;
import christmas.view.input.ConsoleInput;
import christmas.view.output.ConsoleOutputView;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderController extends ConsoleRetryController {

    private final OrderService orderService;
    private final ConsoleOutputView output;

    public OrderController(ConsoleInput input, OrderService orderService, ConsoleOutputView output) {
        super(input);
        this.orderService = orderService;
        this.output = output;
    }

    public void run() {
        final LocalDate localDate = run(() -> requestVisitDate());
        Order order = run(() -> createOrder(localDate));
        displayOrderDetails(order);
        OrderBenefitPrice orderBenefitPrice = displayEventDetails(order);
        displayPaymentResult(orderBenefitPrice, order.getItemsTotalPrice());
    }

    private LocalDate requestVisitDate(){
        return input.requestVisitDate();
    }

    private Order createOrder(LocalDate localDate) {
        List<String> orderItemsForm = input.requestOrderItems();
        List<OrderItem> allItem = ConvertOrderItem.getAllMenu(orderItemsForm);
        OrderItems orderItems = new OrderItems(allItem);
        return new Order(localDate, orderItems);
    }

    private void displayOrderDetails(Order order) {
        output.printStartMessage(order.getLocalDate());
        OrderItems orderItems = order.getOrderItems();
        List<OrderMenuDto> orderMenus = createOrderMenuDto(orderItems);
        output.printAllOrderMenu(orderMenus);
        output.printTotalOrderPrice(order.getItemsTotalPrice());
    }

    private List<OrderMenuDto> createOrderMenuDto(OrderItems orderItems) {
        return orderItems.getOrderItems().stream()
                .map(OrderMenuDto::of)
                .toList();
    }

    private OrderBenefitPrice displayEventDetails(Order order) {
        Optional<GiftItem> giftItem = displayGiftMenu(order);
        List<DiscountInfo> discountInfos = displayBenefitDetails(order, giftItem);
        return displayTotalBenefitPrice(giftItem, discountInfos);
    }

    private Optional<GiftItem> displayGiftMenu(Order order) {
        Optional<GiftItem> giftItem = orderService.getGiftItem(order.getItemsTotalPrice());
        output.printGiftMenu(giftItem);
        return giftItem;
    }

    private List<DiscountInfo> displayBenefitDetails(Order order, Optional<GiftItem> giftItem) {
        List<DiscountInfo> discountInfos = orderService.getDiscountInfos(order);
        List<DiscountInfoWithGiftItemDTO> discountInfosWithGiftItem =
                orderService.addGiftItemToDiscountInfos(discountInfos, giftItem);

        output.printBenefitDetails(discountInfosWithGiftItem);
        return discountInfos;
    }

    private OrderBenefitPrice displayTotalBenefitPrice(Optional<GiftItem> giftItem, List<DiscountInfo> discountInfos) {
        OrderBenefitPrice benefitPrice = getTotalBenefitPrice(discountInfos, giftItem);
        output.printTotalBenefitPrice(benefitPrice.getTotalBenefitPrice());
        return benefitPrice;
    }

    private OrderBenefitPrice getTotalBenefitPrice(List<DiscountInfo> discountInfos, Optional<GiftItem> giftItem) {
        int totalDiscountPrice = orderService.getTotalDiscountPrice(discountInfos);
        int giftItemPrice = 0;

        if (giftItem.isPresent()) {
            giftItemPrice = giftItem.get().getPrice();
        }
        return new OrderBenefitPrice(totalDiscountPrice, giftItemPrice);
    }

    private void displayPaymentResult(OrderBenefitPrice orderBenefitPrice, int totalOrderPrice) {
        output.printEstimatedPayment(totalOrderPrice - orderBenefitPrice.getDiscountPrice());
        String badgeName = getBadgeName(orderBenefitPrice.getTotalBenefitPrice());
        output.printDecemberEventBadge(badgeName);
    }

    private String getBadgeName(int totalBenefitPrice) {
        return orderService.getBadgeName(totalBenefitPrice);
    }

}
