package christmas.service.dto;

import christmas.domain.order.OrderItem;

import java.time.LocalDate;
import java.util.List;

public class DiscountDto {
    private final LocalDate localDate;
    private final List<OrderItem> orderItems;

    public DiscountDto(LocalDate localDate, List<OrderItem> orderItems) {
        this.localDate = localDate;
        this.orderItems = orderItems;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
