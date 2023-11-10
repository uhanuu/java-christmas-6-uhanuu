package christmas.controller;

import christmas.controller.dto.OrderInfoDto;
import christmas.service.OrderService;
import christmas.view.input.ConsoleInput;

import java.time.LocalDate;

public class OrderController {

    private final ConsoleInput input;
    private final OrderService orderService;

    public OrderController(ConsoleInput input, OrderService orderService) {
        this.input = input;
        this.orderService = orderService;
    }

    public OrderInfoDto createOrder() {
        LocalDate localDate = input.requestVisitDate();
        return null;
    }
}
