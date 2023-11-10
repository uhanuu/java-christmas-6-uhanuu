package christmas.controller;

import christmas.domain.order.ConvertOrderItem;
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

    public OrderItems createOrder() {
        LocalDate localDate = input.requestVisitDate();
        List<String> orderItemsForm = input.requestOrderItems();
        List<OrderItem> allItem = ConvertOrderItem.getAllMenu(orderItemsForm);
        return new OrderItems(allItem);
    }

}
