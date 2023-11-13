package christmas.service.dto;

import christmas.domain.order.OrderItem;
import christmas.menu.MenuInfo;

public class DiscountItemDto {

    private final MenuInfo menuInfo;
    private final int quantity;

    private DiscountItemDto(MenuInfo menuInfo, int quantity) {
        this.menuInfo = menuInfo;
        this.quantity = quantity;
    }

    public static DiscountItemDto of(OrderItem orderItem) {
        return new DiscountItemDto(orderItem.getMenuInfo(), orderItem.getQuantity());
    }

    public MenuInfo getMenuInfo() {
        return menuInfo;
    }

    public int getQuantity() {
        return quantity;
    }
}
