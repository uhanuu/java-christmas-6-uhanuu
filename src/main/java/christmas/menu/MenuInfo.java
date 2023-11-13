package christmas.menu;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MenuInfo {

    //애피타이저
    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),

    //메인 메뉴
    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),

    //디저트,
    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),

    //음료
    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private static final Map<String, MenuInfo> MENU_INFO_MAP;
    static {
        MENU_INFO_MAP = Arrays.stream(MenuInfo.values())
                .collect(Collectors.toMap(info -> info.name, Function.identity()));
    }

    private final String name;
    private final int price;


    MenuInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static MenuInfo getMenuInfo(String name) {
        return Optional.ofNullable(MENU_INFO_MAP.get(name))
                .orElseThrow(() -> new IllegalArgumentException( "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
