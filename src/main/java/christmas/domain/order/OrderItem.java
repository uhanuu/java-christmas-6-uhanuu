package christmas.domain.order;

import christmas.menu.MenuInfo;
import christmas.menu.MenuType;

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

    public int getQuantity() {
        return quantity;
    }

    public MenuInfo getMenuInfo() {
        return menuInfo;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "menuInfo=" + menuInfo +
                ", quantity=" + quantity +
                '}';
    }
}
