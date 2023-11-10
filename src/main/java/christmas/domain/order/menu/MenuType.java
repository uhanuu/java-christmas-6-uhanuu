package christmas.domain.order.menu;

import java.util.Collections;
import java.util.List;

public enum MenuType {

    APPETIZER("애피타이저", List.of(MenuInfo.MUSHROOM_SOUP, MenuInfo.TAPAS, MenuInfo.CAESAR_SALAD)),
    MAIN_MENU("메인", List.of(MenuInfo.T_BONE_STEAK, MenuInfo.BBQ_RIBS, MenuInfo.SEAFOOD_PASTA, MenuInfo.CHRISTMAS_PASTA)),
    DESERT("디저트", List.of(MenuInfo.CHOCOLATE_CAKE, MenuInfo.ICE_CREAM)),
    BEVERAGE("음료", List.of(MenuInfo.ZERO_COLA, MenuInfo.RED_WINE, MenuInfo.CHAMPAGNE)),
    EMPTY("없음", Collections.emptyList());

    private final String type;
    private final List<MenuInfo> menuInfo;

    MenuType(String type, List<MenuInfo> menuInfo) {
        this.type = type;
        this.menuInfo = menuInfo;
    }

    public static boolean isBeverage(MenuInfo menuInfo) {
        return BEVERAGE.menuInfo.stream()
                .filter(beverage -> beverage.equals(menuInfo))
                .findAny()
                .isPresent();
    }

}
