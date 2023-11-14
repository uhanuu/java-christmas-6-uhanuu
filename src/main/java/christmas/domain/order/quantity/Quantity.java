package christmas.domain.order.quantity;

import static christmas.domain.order.constants.OrderQuantityConstraint.MAX_MENU_COUNT;
import static christmas.domain.order.constants.OrderQuantityConstraint.MIN_MENU_COUNT;

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
            throw new IllegalArgumentException(
                    String.format(MAX_MENU_COUNT.getErrorMessage(), MAX_MENU_COUNT.getLimit()));
        }
    }

    public int getQuantity() {
        return quantity;
    }
}

