package christmas.domain.order;

import christmas.domain.menu.MenuInfo;
import christmas.domain.menu.MenuType;

public class OrderItem {
    private final MenuInfo menuInfo;
    private final int quantity;

    public OrderItem(MenuInfo menuInfo, int quantity) {
        this.menuInfo = menuInfo;
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return menuInfo.getPrice() * quantity;
    }

    public boolean isBeverage() {
        return MenuType.isBeverage(menuInfo);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "menuInfo=" + menuInfo +
                ", quantity=" + quantity +
                '}';
    }
}
