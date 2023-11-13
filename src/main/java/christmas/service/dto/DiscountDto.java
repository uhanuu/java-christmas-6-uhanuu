package christmas.service.dto;

import java.time.LocalDate;
import java.util.List;

public class DiscountDto {
    private final LocalDate localDate;
    private final List<DiscountItemDto> orderItems;
    private final int totalPrice;

    public DiscountDto(LocalDate localDate, List<DiscountItemDto> orderItems, int totalPrice) {
        this.localDate = localDate;
        this.orderItems = orderItems;
        this.totalPrice = totalPrice;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }


    public int getTotalPrice() {
        return totalPrice;
    }

    public List<DiscountItemDto> getOrderItems() {
        return orderItems;
    }
}
