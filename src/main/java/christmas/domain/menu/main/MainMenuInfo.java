package christmas.domain.menu.main;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum MainMenuInfo {

    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000);

    private static final Map<String, MainMenuInfo> MAIN_MENU_INFO_MAP;
    static {
        MAIN_MENU_INFO_MAP = Arrays.stream(MainMenuInfo.values())
                .collect(Collectors.toMap(info -> info.name, Function.identity()));
    }
    private final String name;
    private final int price;

    MainMenuInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static MainMenuInfo getInfo(String name) {
        return Optional.ofNullable(MAIN_MENU_INFO_MAP.get(name))
                .orElseThrow(() -> new IllegalArgumentException("해당 이름에 요리가 존재하지 않습니다."));
    }

    public static boolean isContains(String name) {
        return Arrays.stream(MainMenuInfo.values())
                .filter(info -> info.name.equals(name))
                .findAny()
                .isPresent();
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
