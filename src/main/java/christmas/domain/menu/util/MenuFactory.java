package christmas.domain.menu.util;

import christmas.domain.menu.Menu;
import christmas.domain.menu.appetizer.Appetizer;
import christmas.domain.menu.appetizer.AppetizerInfo;

public class MenuFactory {

    private MenuFactory() {
    }

    public static Menu getMenu(String name) {
        AppetizerInfo info = AppetizerInfo.getInfo(name);
        return new Appetizer(info.getName(), info.getPrice());
    }
}
