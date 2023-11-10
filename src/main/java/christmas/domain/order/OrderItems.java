package christmas.domain.order;

import java.util.Collections;
import java.util.List;

public class OrderItems {

    private final List<OrderItem> orderItems;

    public OrderItems(List<OrderItem> orderItems) {
        validateOrderItems(orderItems);
        this.orderItems = orderItems;
        validateTotalQuantity();
    }

    private void validateOrderItems(List<OrderItem> orderItems) {
        int itemSize = orderItems.size();
        int beverageCount = getBeverageCount(orderItems);

        if (itemSize == beverageCount) {
            throw new IllegalArgumentException("음료만 주문할 수 없습니다.");
        }
    }

    private int getBeverageCount(List<OrderItem> orderItems) {
        return (int) orderItems.stream()
                .filter(OrderItem::isBeverage)
                .count();
    }

    private void validateTotalQuantity() {
        int totalQuantity = getTotalQuantity();

        if (totalQuantity > 20) {
            throw new IllegalArgumentException("주문은 최대 20개 까지만 가능합니다.");
        }
    }

    private int getTotalQuantity() {
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
