package christmas.controller.dto;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;

import java.time.LocalDate;

public class DiscountOrderServiceDto {

    private final LocalDate localDate;
    private final OrderItems orderItems;
    private DiscountOrderServiceDto(LocalDate localDate, OrderItems orderItems) {
        this.localDate = localDate;
        this.orderItems = orderItems;
    }

    public static DiscountOrderServiceDto of(Order order) {
        return new DiscountOrderServiceDto(
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
