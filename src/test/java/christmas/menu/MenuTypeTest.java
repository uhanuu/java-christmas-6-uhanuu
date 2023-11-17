package christmas.menu;


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
    @DisplayName("요리의 종류가 음료가 아니면 false를 반환한다.")
    @ValueSource(strings = {"양송이수프", "티본스테이크", "시저샐러드","초코케이크","아이스크림"})
    public void isBeverageFalseTest(String input) {
        //given
        MenuInfo menuInfo = MenuInfo.getMenuInfo(input);
        // when
        boolean isBeverage = MenuType.isBeverage(menuInfo);
        // then
        Assertions.assertThat(isBeverage).isFalse();
    }

    @ParameterizedTest(name = "{0}은 메인음식이다.")
    @DisplayName("요리의 종류가 메인음식이면 true를 반환한다.")
    @ValueSource(strings = {"티본스테이크", "바비큐립", "해산물파스타", "크리스마스파스타"})
    public void isMainMenuTrueTest(String input) {
        //given
        MenuInfo menuInfo = MenuInfo.getMenuInfo(input);
        // when
        boolean isMainMenu = MenuType.isMainMenu(menuInfo);
        // then
        Assertions.assertThat(isMainMenu).isTrue();
    }

    @ParameterizedTest(name = "{0}은 메인음식이 아니다.")
    @DisplayName("요리의 종류가 메인음식이 아니면 false를 반환한다.")
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드","초코케이크","아이스크림"})
    public void isMainMenuFalseTest(String input) {
        //given
        MenuInfo menuInfo = MenuInfo.getMenuInfo(input);
        // when
        boolean isMainMenu = MenuType.isMainMenu(menuInfo);
        // then
        Assertions.assertThat(isMainMenu).isFalse();
    }

    @ParameterizedTest(name = "{0}은 디저트음식이다.")
    @DisplayName("요리의 종류가 디저트음식이면 true를 반환한다.")
    @ValueSource(strings = {"초코케이크", "아이스크림"})
    public void isDesertTrueTest(String input) {
        //given
        MenuInfo menuInfo = MenuInfo.getMenuInfo(input);
        // when
        boolean isDesert = MenuType.isDesert(menuInfo);
        // then
        Assertions.assertThat(isDesert).isTrue();
    }

    @ParameterizedTest(name = "{0}은 디저트음식이 아니다.")
    @DisplayName("요리의 종류가 디저트음식이 아니면 false를 반환한다.")
    @ValueSource(strings = {"양송이수프", "타파스", "시저샐러드","제로콜라","샴페인"})
    public void isDesertFalseTest(String input) {
        //given
        MenuInfo menuInfo = MenuInfo.getMenuInfo(input);
        // when
        boolean isDesert = MenuType.isDesert(menuInfo);
        // then
        Assertions.assertThat(isDesert).isFalse();
    }
}