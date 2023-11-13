package christmas.domain.order.quantity;

import static christmas.domain.order.OrderConstraint.MAX_MENU_COUNT;
import static christmas.domain.order.OrderConstraint.MIN_MENU_COUNT;

public class Quantity {

    private final int quantity;

    public Quantity(int quantity) {
        validateQuantity(quantity);
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < MIN_MENU_COUNT.getLimit()) {
            throw new IllegalArgumentException(MIN_MENU_COUNT.getErrorMessage());
        }
        if (quantity > MAX_MENU_COUNT.getLimit()) {
            throw new IllegalArgumentException(MAX_MENU_COUNT.getErrorMessage());
        }
    }

    public int getQuantity() {
        return quantity;
    }
}

