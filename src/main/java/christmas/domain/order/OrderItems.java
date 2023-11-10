package christmas.domain.order;

import java.util.Collections;
import java.util.List;

public class OrderItems {

    private static final int MINIMUM_ORDER_AMOUNT = 10000;
    private final List<OrderItem> orderItems;

    public OrderItems(List<OrderItem> orderItems) {
        validateOrderItems(orderItems);
        this.orderItems = orderItems;
        validateTotalPrice();

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

    private void validateTotalPrice() {
        int totalPrice = getTotalPrice();

        if (totalPrice < MINIMUM_ORDER_AMOUNT) {
            throw new IllegalArgumentException("최소 주문금액은 +" + MINIMUM_ORDER_AMOUNT + "+원 입니다.");
        }
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
