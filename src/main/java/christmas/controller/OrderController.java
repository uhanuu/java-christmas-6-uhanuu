package christmas.controller;

import christmas.domain.order.OrderItem;
import christmas.view.input.ConsoleInput;

import java.time.LocalDate;
import java.util.List;

public class OrderController {

    private final ConsoleInput input;

    public OrderController(ConsoleInput input) {
        this.input = input;
    }

    public OrderItem createOrder() {
        LocalDate localDate = input.requestVisitDate();
        List<String> orderItemsForm = input.requestOrderItems();



        return null;
    }
}
