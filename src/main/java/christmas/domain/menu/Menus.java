package christmas.domain.menu;

import java.util.Collections;
import java.util.List;

public class Menus {

    private final List<Menu> menus;

    public Menus(List<Menu> menus) {
        this.menus = menus;
    }

    public int totalPrice() {
        return menus.stream()
                //get보다는 객체에게 메시지하기 menu에서 수량 고민하기 중간에 Menu와 수량을 가지는 객체를 만들어도 좋을거 같다.
                .mapToInt(Menu::getPrice)
                .sum();
    }

    public List<Menu> getMenus() {
        return Collections.unmodifiableList(menus);
    }
}
