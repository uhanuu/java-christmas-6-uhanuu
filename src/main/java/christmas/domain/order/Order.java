package christmas.domain.order;

import christmas.domain.menu.MenuInfo;

import java.time.LocalDate;

public class Order {
    private final LocalDate localDate;
    private final OrderItem orderItem;

    public Order(LocalDate localDate, OrderItem orderItem) {
        this.localDate = localDate;
        this.orderItem = orderItem;
    }
}
