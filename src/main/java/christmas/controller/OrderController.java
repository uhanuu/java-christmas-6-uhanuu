package christmas.controller;

import christmas.controller.dto.ChristMaxOrderDto;
import christmas.domain.order.Order;
import christmas.service.ChristMasOrderService;
import christmas.util.ConvertOrderItem;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;
import christmas.view.input.ConsoleInput;

import java.time.LocalDate;
import java.util.List;

public class OrderController {

    private final ConsoleInput input;
    private final ChristMasOrderService christMasOrderService;

    public OrderController(ConsoleInput input, ChristMasOrderService christMasOrderService) {
        this.input = input;
        this.christMasOrderService = christMasOrderService;
    }

    public ChristMaxOrderDto createOrder() {
        LocalDate localDate = input.requestVisitDate();
        List<String> orderItemsForm = input.requestOrderItems();
        List<OrderItem> allItem = ConvertOrderItem.getAllMenu(orderItemsForm);

        return ChristMaxOrderDto.of(createOrder(localDate, allItem));
    }

    private Order createOrder(LocalDate localDate, List<OrderItem> allItem) {
        OrderItems orderItems = new OrderItems(allItem);
        return new Order(localDate, orderItems);
    }

    public void getResult(ChristMaxOrderDto christMaxOrderDto) {
        christMasOrderService.christMaxDiscountPrice(christMaxOrderDto);
    }

}
