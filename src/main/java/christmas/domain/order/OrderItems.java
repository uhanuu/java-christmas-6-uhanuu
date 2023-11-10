package christmas.domain.order;

import java.util.List;

public class OrderItems {

    private final List<OrderItem> orderItems;

    public OrderItems(List<OrderItem> orderItems) {
        validateOrderItems(orderItems);
        this.orderItems = orderItems;
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
}
