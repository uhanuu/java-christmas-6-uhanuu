package christmas.domain.order;

import christmas.domain.menu.MenuInfo;

public class OrderItem {
    private String name;
    private int quantity;

    public OrderItem(String name, int quantity) {
        validateOrderItemName(name);
        validatePositiveQuantity(quantity);
        this.name = name;
        this.quantity = quantity;
    }

    private void validateOrderItemName(String name) {
        if (!MenuInfo.isContains(name)) {
            throw new IllegalArgumentException();
        }
    }

    private void validatePositiveQuantity(int quantity) {
        if (0 > quantity) {
            throw new IllegalStateException();
        }
    }
}
