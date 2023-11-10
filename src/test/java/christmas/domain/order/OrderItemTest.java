//package christmas.domain.order;
//
//import christmas.domain.menu.Menu;
//import christmas.domain.menu.appetizer.Appetizer;
//import christmas.domain.menu.desert.Desert;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//class OrderItemTest {
//
//    @Test
//    @DisplayName("전체 메뉴의 할인 전 가격을 반환할 수 있다.")
//    public void totalPriceTest() {
//        // given
//        Menu menu1 = createMenus("양송이", 8000);
//        Menu menu2 = createMenus("양송이", 8000);
//        new OrderItem(new )
//        // when
//        int totalPrice = menus.getTotalPrice();
//        // then
//        assertThat(11000).isEqualTo(totalPrice);
//    }
//
//    private Menu createMenus(String name, int price) {
//        return new Appetizer(name, price);
//    }
//
//}