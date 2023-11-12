package christmas.controller;

import christmas.controller.dto.DiscountOrderServiceDto;
import christmas.controller.dto.OrderMenuDto;
import christmas.domain.order.Order;
import christmas.service.OrderService;
import christmas.util.ConvertOrderItem;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;
import christmas.view.input.ConsoleInput;
import christmas.view.output.ConsoleOutputView;

import java.time.LocalDate;
import java.util.List;

public class OrderController {

    private final ConsoleInput input;
    private final OrderService orderService;
    private final ConsoleOutputView output;

    public OrderController(ConsoleInput input, OrderService orderService, ConsoleOutputView output) {
        this.input = input;
        this.orderService = orderService;
        this.output = output;
    }

    public DiscountOrderServiceDto createOrder() {
        LocalDate localDate = input.requestVisitDate();
        List<String> orderItemsForm = input.requestOrderItems();

        List<OrderItem> allItem = ConvertOrderItem.getAllMenu(orderItemsForm);
        return DiscountOrderServiceDto.of(createOrder(localDate, allItem));
    }

    private Order createOrder(LocalDate localDate, List<OrderItem> allItem) {
        OrderItems orderItems = new OrderItems(allItem);
        return new Order(localDate, orderItems);
    }

    public void printOrderMenu(OrderItems orderItems) {
        List<OrderMenuDto> orderMenus = createOrderMenuDto(orderItems);
    }

    private List<OrderMenuDto> createOrderMenuDto(OrderItems orderItems) {
        return orderItems.getOrderItems().stream()
                .map(OrderMenuDto::of)
                .toList();
    }


}
