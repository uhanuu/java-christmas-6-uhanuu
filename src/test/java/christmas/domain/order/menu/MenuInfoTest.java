package christmas.domain.order.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class MenuInfoTest {

    @ParameterizedTest(name = "{0}의 요리는 메뉴의 존재한다.")
    @DisplayName("메뉴에 존재하면 메뉴의 정보를 반환할 수 있다.")
    @ValueSource(strings = {"양송이수프", "바비큐립", "초코케이크", "샴페인", "타파스"})
    public void getInfoTest(String input) {
        // when
        MenuInfo menuInfo = MenuInfo.getMenuInfo(input);
        // then
        assertThat(menuInfo.getName()).isEqualTo(input);
    }

    @ParameterizedTest(name = "{0}의 요리는 메뉴의 존재하지 않는다.")
    @DisplayName("메뉴에 존재하지 않으면 예외가 발생한다.")
    @ValueSource(strings = {"내가만든쿠키","너를위해","만들었어","난","쿠키","싫어해"})
    public void getInfoExceptionTest(String input) {
        // when // then
        assertThatThrownBy(() -> MenuInfo.getMenuInfo(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

}