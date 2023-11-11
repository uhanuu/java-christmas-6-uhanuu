package christmas.controller.dto;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;

import java.time.LocalDate;
import java.util.List;

public class ChristMaxOrderDto {

    private final LocalDate localDate;
    private final List<OrderItem> orderItems;
    private ChristMaxOrderDto(LocalDate localDate, List<OrderItem> orderItems) {
        this.localDate = localDate;
        this.orderItems = orderItems;
    }

    public static ChristMaxOrderDto of(Order order) {
        return new ChristMaxOrderDto(
                order.getLocalDate(),
                order.getOrderItems());
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
