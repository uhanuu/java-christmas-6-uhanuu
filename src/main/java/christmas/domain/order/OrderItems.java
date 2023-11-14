package christmas.domain.order;

import christmas.domain.order.constants.OrderMenuErrorMessage;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static christmas.domain.order.constants.OrderQuantityConstraint.MAX_MENU_COUNT;

public class OrderItems {

    private final List<OrderItem> orderItems;

    public OrderItems(List<OrderItem> orderItems) {
        validateBeverageItems(orderItems);
        validateTotalQuantity(orderItems);
        validateDuplicateOrderItems(orderItems);
        this.orderItems = orderItems;
    }

    private void validateBeverageItems(List<OrderItem> orderItems) {
        int itemSize = orderItems.size();
        int beverageItemCount = getBeverageItemCount(orderItems);

        if (itemSize == beverageItemCount) {
            throw new IllegalArgumentException(OrderMenuErrorMessage.NOT_BEVERAGE_ONLY_ERROR_MESSAGE.getErrorMessage());
        }
    }

    private int getBeverageItemCount(List<OrderItem> orderItems) {
        return (int) orderItems.stream()
                .filter(OrderItem::isBeverage)
                .count();
    }

    private void validateTotalQuantity(List<OrderItem> orderItems) {
        int totalQuantity = getTotalQuantity(orderItems);

        if (totalQuantity > MAX_MENU_COUNT.getLimit()) {
            throw new IllegalArgumentException(
                    String.format(MAX_MENU_COUNT.getErrorMessage(), MAX_MENU_COUNT.getLimit()));
        }
    }

    private void validateDuplicateOrderItems(List<OrderItem> orderItems) {
        Set<OrderItem> uniqueItems = new HashSet<>(orderItems);

        if (uniqueItems.size() != orderItems.size()) {
            throw new IllegalArgumentException(OrderMenuErrorMessage.DUPLICATE_MENU_ERROR_MESSAGE.getErrorMessage());
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
