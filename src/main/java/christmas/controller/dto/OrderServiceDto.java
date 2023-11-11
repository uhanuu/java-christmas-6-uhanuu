package christmas.controller.dto;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;

import java.time.LocalDate;

public class OrderServiceDto {

    private final LocalDate localDate;
    private final OrderItems orderItems;
    private OrderServiceDto(LocalDate localDate, OrderItems orderItems) {
        this.localDate = localDate;
        this.orderItems = orderItems;
    }

    public static OrderServiceDto of(Order order) {
        return new OrderServiceDto(
                order.getLocalDate(),
                order.getOrderItems());
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public OrderItems getOrderItems() {
        return orderItems;
    }
}
