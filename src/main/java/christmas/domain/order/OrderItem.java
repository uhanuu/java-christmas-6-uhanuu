package christmas.domain.order;

import christmas.domain.order.quantity.Quantity;
import christmas.menu.MenuInfo;
import christmas.menu.MenuType;

public class OrderItem {
    private final MenuInfo menuInfo;
    private final Quantity quantity;

    public OrderItem(MenuInfo menuInfo, int quantity) {
        this.menuInfo = menuInfo;
        this.quantity = new Quantity(quantity);
    }

    public int getTotalPrice() {
        return menuInfo.getPrice() * getQuantity();
    }

    public boolean isBeverage() {
        return MenuType.isBeverage(menuInfo);
    }

    public int getQuantity() {
        return quantity.getQuantity();
    }

    public MenuInfo getMenuInfo() {
        return menuInfo;
    }

}
