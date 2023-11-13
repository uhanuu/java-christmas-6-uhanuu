package christmas.util;

import christmas.domain.order.OrderItem;
import christmas.menu.MenuInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.tuple;

class ConvertOrderItemTest {
    
    @Test
    @DisplayName("하이폰을 기준으로 메뉴의 이름과 수량을 분리해서 저장할 수 있다.")
    public void getAllMenu() {
        // given
        List<String> requestForm = List.of("양송이수프-1", "시저샐러드-3");
        // when
        List<OrderItem> allMenu = ConvertOrderItem.getAllMenu(requestForm);
        // then
        assertThat(allMenu).hasSize(2)
                .extracting("menuInfo", "quantity")
                .contains(
                        tuple(MenuInfo.MUSHROOM_SOUP, 1),
                        tuple(MenuInfo.CAESAR_SALAD, 3)
                );
    }

    @Test
    @DisplayName("메뉴의 해당하지 않는 이름이면 예외가 발생한다.")
    public void getAllMenuException() {
        // given
        List<String> requestForm = List.of("양송이수프-1", "내가만든쿠키-1");
        // when // then
        assertThatThrownBy(() -> ConvertOrderItem.getAllMenu(requestForm))
                .isInstanceOf(IllegalArgumentException.class);
    }

}