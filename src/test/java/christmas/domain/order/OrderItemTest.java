package christmas.domain.order;

import christmas.domain.order.menu.MenuInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OrderItemTest {

    @Test
    @DisplayName("Item의 총 금액을 반환받을 수 있다.")
    public void getTotalPrice() {
        // given
        MenuInfo bbqRibs = MenuInfo.BBQ_RIBS;
        int quantity = 3;
        OrderItem orderItem = createOrderItem(bbqRibs, quantity);
        // when
        int totalPrice = orderItem.getTotalPrice();
        // then
        assertThat(totalPrice).isEqualTo(bbqRibs.getPrice() * quantity);
    }

    @Test
    @DisplayName("Item이 음료인지 확인할 수 있다.")
    public void isBeverage() {
        // given
        MenuInfo zeroCola = MenuInfo.ZERO_COLA;
        OrderItem orderItem = createOrderItem(zeroCola, 1);
        // when
        boolean isBeverage = orderItem.isBeverage();
        // then
        assertThat(isBeverage).isTrue();
    }

    private OrderItem createOrderItem(MenuInfo menuInfo, int quantity) {
        return new OrderItem(menuInfo, quantity);
    }

}