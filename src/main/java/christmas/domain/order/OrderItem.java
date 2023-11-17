package christmas.domain.order;

import christmas.domain.order.quantity.Quantity;
import christmas.menu.MenuInfo;
import christmas.menu.MenuType;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return menuInfo == orderItem.menuInfo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuInfo);
    }
}
