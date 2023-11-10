package christmas.domain.order;

import christmas.domain.menu.MenuInfo;

import java.util.Map;

public class OrderItems {
    private final Map<MenuInfo, Integer> orderItems;

    public OrderItems(Map<MenuInfo, Integer> orderItems) {
        this.orderItems = orderItems;
    }
}
