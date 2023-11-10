package christmas.domain.order;

import java.time.LocalDate;

public class Order {
    private final LocalDate localDate;
    private final OrderItems orderItems;

    public Order(LocalDate localDate, OrderItems orderItems) {
        this.localDate = localDate;
        this.orderItems = orderItems;
    }
}
