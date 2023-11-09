package christmas.domain.menu.util;

import christmas.domain.menu.Menu;
import christmas.domain.menu.appetizer.Appetizer;
import christmas.domain.menu.beverage.Beverage;
import christmas.domain.menu.desert.Desert;
import christmas.domain.menu.main.MainMenu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MenuFactoryTest {

    @DisplayName("팩토리 메서드를 통해서 특정 메뉴를 생성할 수 있다.")
    @TestFactory
    Collection<DynamicTest> getMenu() {
        // given
        return List.of(
                DynamicTest.dynamicTest("애피타이저를 생성할 수 있다.", () -> {
                    //given
                    String name = "양송이수프";
                    int price = 6000;
                    //when
                    Menu appetizer = MenuFactory.getMenu(name);
                    //then
                    assertThat((Appetizer) appetizer).isInstanceOf(Appetizer.class);
                    assertThat(appetizer.getName()).isEqualTo(name);
                    assertThat(appetizer.getPrice()).isEqualTo(price);
                })
                ,
                DynamicTest.dynamicTest("음료를 생성할 수 있다.", () -> {
                    //given
                    String name = "제로콜라";
                    int price = 3000;
                    //when
                    Menu beverage = MenuFactory.getMenu(name);
                    //then
                    assertThat((Beverage) beverage).isInstanceOf(Beverage.class);
                    assertThat(beverage.getName()).isEqualTo(name);
                    assertThat(beverage.getPrice()).isEqualTo(price);
                }),
                DynamicTest.dynamicTest("디저트를 생성할 수 있다.", () -> {
                    //given
                    String name = "초코케이크";
                    int price = 15000;
                    //when
                    Menu desert = MenuFactory.getMenu(name);
                    //then
                    assertThat((Desert) desert).isInstanceOf(Desert.class);
                    assertThat(desert.getName()).isEqualTo(name);
                    assertThat(desert.getPrice()).isEqualTo(price);
                }),
                DynamicTest.dynamicTest("메인 음식 생성할 수 있다.", () -> {
                    //when
                    String name = "티본스테이크";
                    int price = 55000;
                    Menu mainMenu = MenuFactory.getMenu(name);
                    //then
                    assertThat((MainMenu) mainMenu).isInstanceOf(MainMenu.class);
                    assertThat(mainMenu.getName()).isEqualTo(name);
                    assertThat(mainMenu.getPrice()).isEqualTo(price);
                })
        );
    }

}