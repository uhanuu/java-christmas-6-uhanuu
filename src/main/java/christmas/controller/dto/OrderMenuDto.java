package christmas.controller.dto;

import christmas.domain.order.OrderItem;
import christmas.menu.MenuInfo;

public class OrderMenuDto {
    private final String name;
    private final int quantity;

    private OrderMenuDto(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public static OrderMenuDto of(OrderItem orderItem) {
        MenuInfo menuInfo = orderItem.getMenuInfo();
        return new OrderMenuDto(menuInfo.getName(), orderItem.getQuantity());
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}
