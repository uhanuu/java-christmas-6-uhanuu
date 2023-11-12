package christmas.service.dto;

import christmas.domain.order.OrderItem;

import java.time.LocalDate;
import java.util.List;

public class DiscountDto {
    private final LocalDate localDate;
    private final List<OrderItem> orderItems;
    private final int totalPrice;

    public DiscountDto(LocalDate localDate, List<OrderItem> orderItems, int totalPrice) {
        this.localDate = localDate;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
