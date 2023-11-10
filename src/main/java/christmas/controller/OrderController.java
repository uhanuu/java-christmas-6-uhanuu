package christmas.controller;

import christmas.domain.order.Order;
import christmas.util.ConvertOrderItem;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;
import christmas.view.input.ConsoleInput;

import java.time.LocalDate;
import java.util.List;

public class OrderController {

    private final ConsoleInput input;

    public OrderController(ConsoleInput input) {
        this.input = input;
    }

    public Order createOrder() {
        LocalDate localDate = input.requestVisitDate();
        List<String> orderItemsForm = input.requestOrderItems();
        List<OrderItem> allItem = ConvertOrderItem.getAllMenu(orderItemsForm);
        return new Order(localDate, new OrderItems(allItem));
    }

}
