package christmas.controller;

import christmas.controller.dto.OrderServiceDto;
import christmas.domain.order.Order;
import christmas.service.OrderService;
import christmas.util.ConvertOrderItem;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;
import christmas.view.input.ConsoleInput;

import java.time.LocalDate;
import java.util.List;

public class OrderController {

    private final ConsoleInput input;
    private final OrderService orderService;

    public OrderController(ConsoleInput input, OrderService orderService) {
        this.input = input;
        this.orderService = orderService;
    }

    public OrderServiceDto createOrder() {
        LocalDate localDate = input.requestVisitDate();
        List<String> orderItemsForm = input.requestOrderItems();
        List<OrderItem> allItem = ConvertOrderItem.getAllMenu(orderItemsForm);

        return OrderServiceDto.of(createOrder(localDate, allItem));
    }

    private Order createOrder(LocalDate localDate, List<OrderItem> allItem) {
        OrderItems orderItems = new OrderItems(allItem);
        return new Order(localDate, orderItems);
    }

    public void disCountEvent(OrderServiceDto orderServiceDto) {
    }

}
