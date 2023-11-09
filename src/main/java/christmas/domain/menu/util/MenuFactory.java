package christmas.domain.menu.util;

import christmas.domain.menu.Menu;
import christmas.domain.menu.appetizer.Appetizer;
import christmas.domain.menu.appetizer.AppetizerInfo;
import christmas.domain.menu.beverage.Beverage;
import christmas.domain.menu.beverage.BeverageInfo;
import christmas.domain.menu.desert.Desert;
import christmas.domain.menu.desert.DesertInfo;
import christmas.domain.menu.main.MainMenu;
import christmas.domain.menu.main.MainMenuInfo;

public class MenuFactory {

    private MenuFactory() {
    }

    public static Menu getMenu(String name) {
        if (AppetizerInfo.isContains(name)) {
            AppetizerInfo info = AppetizerInfo.getInfo(name);
            return new Appetizer(info.getName(), info.getPrice());
        }
        if (BeverageInfo.isContains(name)) {
            BeverageInfo info = BeverageInfo.getInfo(name);
            return new Beverage(info.getName(), info.getPrice());
        }
        if (DesertInfo.isContains(name)) {
            DesertInfo info = DesertInfo.getInfo(name);
            return new Desert(info.getName(), info.getPrice());
        }
        if (MainMenuInfo.isContains(name)) {
            MainMenuInfo info = MainMenuInfo.getInfo(name);
            return new MainMenu(info.getName(), info.getPrice());
        }
        throw new IllegalArgumentException("error");
    }

}
