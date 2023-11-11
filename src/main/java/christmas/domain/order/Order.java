package christmas.domain.order;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private final LocalDate localDate;
    private final OrderItems orderItems;

    public Order(LocalDate localDate, OrderItems orderItems) {
        this.localDate = localDate;
        this.orderItems = orderItems;
    }

    public int getItemsTotalPrice() {
        return orderItems.getTotalPrice();
    }

    public List<OrderItem> getOrderItems() {
        return orderItems.getOrderItems();
    }

    public LocalDate getLocalDate() {
        return localDate;
    }
}
