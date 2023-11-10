package christmas.domain.order.menu;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MenuTypeTest {

    @ParameterizedTest(name = "{0}은 음료다.")
    @DisplayName("요리의 종류가 음료면 true를 반환한다.")
    @ValueSource(strings = {"제로콜라", "레드와인", "샴페인"})
    public void isBeverageTrueTest(String input) {
        //given
        MenuInfo menuInfo = MenuInfo.getMenuInfo(input);
        // when
        boolean isBeverage = MenuType.isBeverage(menuInfo);
        // then
        Assertions.assertThat(isBeverage).isTrue();
    }

    @ParameterizedTest(name = "{0}은 음료가 아니다.")
    @DisplayName("요리의 종류가 음료면 false를 반환한다.")
    @ValueSource(strings = {"양송이수프", "티본스테이크", "시저샐러드","초코케이크","아이스크림"})
    public void isBeverageFalseTest(String input) {
        //given
        MenuInfo menuInfo = MenuInfo.getMenuInfo(input);
        // when
        boolean isBeverage = MenuType.isBeverage(menuInfo);
        // then
        Assertions.assertThat(isBeverage).isFalse();
    }
}