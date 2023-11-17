package christmas.domain.order;

import christmas.menu.MenuInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderItemsTest {

    @Test
    @DisplayName("모든 메뉴의 금액을 계산할 수 있다.")
    public void getTotalPrice() {
        // given
        OrderItem orderItem1 = createOrderItem(MenuInfo.BBQ_RIBS, 1);
        OrderItem orderItem2 = createOrderItem(MenuInfo.CHOCOLATE_CAKE, 3);
        OrderItems orderItems = createOrderItems(orderItem1, orderItem2);
        // when
        int totalPrice = orderItems.getTotalPrice();
        // then
        int itemsTotalPrice = orderItem1.getTotalPrice() + orderItem2.getTotalPrice();
        assertThat(totalPrice).isEqualTo(itemsTotalPrice);
    }

    @Test
    @DisplayName("음료만 주문시 예외가 발생한다.")
    public void validateBeverageItems() {
        // given
        OrderItem orderItem1 = createOrderItem(MenuInfo.CHAMPAGNE, 1);
        OrderItem orderItem2 = createOrderItem(MenuInfo.RED_WINE, 1);
        // when // then
        assertThatThrownBy(() -> new OrderItems(List.of(orderItem1, orderItem2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문 메뉴의 총 개수가 20개가 넘어가면 예외가 발생한다.")
    public void validateTotalQuantity() {
        // given
        OrderItem orderItem1 = createOrderItem(MenuInfo.MUSHROOM_SOUP, 10);
        OrderItem orderItem2 = createOrderItem(MenuInfo.ZERO_COLA, 11);
        // when // then
        assertThatThrownBy(() -> new OrderItems(List.of(orderItem1, orderItem2)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("주문 메뉴는 중복될 수 없다.")
    public void validateDuplicateOrderItems() {
        // given
        OrderItem orderItem1 = createOrderItem(MenuInfo.MUSHROOM_SOUP, 1);
        OrderItem orderItem2 = createOrderItem(MenuInfo.MUSHROOM_SOUP, 5);
        // when // then
        assertThatThrownBy(() -> new OrderItems(List.of(orderItem1, orderItem2)))
                .isInstanceOf(IllegalArgumentException.class);
    }
    

    private OrderItems createOrderItems(OrderItem... items) {
        return new OrderItems(List.of(items));
    }

    private OrderItem createOrderItem(MenuInfo menuInfo, int quantity) {
        return new OrderItem(menuInfo, quantity);
    }

}