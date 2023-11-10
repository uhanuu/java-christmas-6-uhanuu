package christmas.domain.menu;

import christmas.domain.menu.appetizer.Appetizer;
import christmas.domain.menu.desert.Desert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MenusTest {

    @Test
    @DisplayName("전체 메뉴의 할인 전 가격을 반환할 수 있다.")
    public void totalPriceTest() {
        // given
        Menus menus = new Menus(createMenus());
        // when
        int totalPrice = menus.getTotalPrice();
        // then
        assertThat(11000).isEqualTo(totalPrice);
    }

    private static List<Menu> createMenus() {
        return List.of(new Appetizer("양송이", 8000), new Desert("아이스크림", 3000));
    }

}