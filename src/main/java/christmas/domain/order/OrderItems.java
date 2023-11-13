package christmas.domain.order;

import java.util.Collections;
import java.util.List;

import static christmas.domain.order.OrderConstraint.MAX_MENU_COUNT;

public class OrderItems {

    private final List<OrderItem> orderItems;

    public OrderItems(List<OrderItem> orderItems) {
        validateOrderItems(orderItems);
        validateTotalQuantity(orderItems);
        this.orderItems = orderItems;
    }

    private void validateOrderItems(List<OrderItem> orderItems) {
        int itemSize = orderItems.size();
        int beverageItemCount = getBeverageItemCount(orderItems);

        OrderConstraint.validateNotBeverageOnly(itemSize, beverageItemCount);
    }

    private int getBeverageItemCount(List<OrderItem> orderItems) {
        return (int) orderItems.stream()
                .filter(OrderItem::isBeverage)
                .count();
    }

    private void validateTotalQuantity(List<OrderItem> orderItems) {
        int totalQuantity = getTotalQuantity(orderItems);

        if (totalQuantity > MAX_MENU_COUNT.getLimit()) {
            throw new IllegalArgumentException(MAX_MENU_COUNT.getErrorMessage());
        }
    }

    private int getTotalQuantity(List<OrderItem> orderItems) {
        return orderItems.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(OrderItem::getTotalPrice)
                .sum();
    }

    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }
}
