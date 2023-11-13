package christmas.domain.order;

import christmas.menu.MenuInfo;
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

    @Test
    @DisplayName("메인음식은 음료가 아니다.")
    public void isBeverageByMainMenu() {
        // given
        MenuInfo christmasPasta = MenuInfo.CHRISTMAS_PASTA;
        OrderItem orderItem = createOrderItem(christmasPasta, 1);
        // when
        boolean isBeverage = orderItem.isBeverage();
        // then
        assertThat(isBeverage).isFalse();
    }

    @Test
    @DisplayName("디저트음식은 음료가 아니다.")
    public void isBeverageByDesert() {
        // given
        MenuInfo iceCream = MenuInfo.ICE_CREAM;
        OrderItem orderItem = createOrderItem(iceCream, 1);
        // when
        boolean isBeverage = orderItem.isBeverage();
        // then
        assertThat(isBeverage).isFalse();
    }

    @Test
    @DisplayName("애피타이저 음료가 아니다.")
    public void isBeverageByAppetizer() {
        // given
        MenuInfo caesarSalad = MenuInfo.CAESAR_SALAD;
        OrderItem orderItem = createOrderItem(caesarSalad, 1);
        // when
        boolean isBeverage = orderItem.isBeverage();
        // then
        assertThat(isBeverage).isFalse();
    }

    private OrderItem createOrderItem(MenuInfo menuInfo, int quantity) {
        return new OrderItem(menuInfo, quantity);
    }

}