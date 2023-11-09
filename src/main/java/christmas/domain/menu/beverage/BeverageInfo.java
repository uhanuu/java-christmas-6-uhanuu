package christmas.domain.menu.beverage;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum BeverageInfo {

    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private static final Map<String, BeverageInfo> BEVERAGE_INFO_MAP;
    static {
        BEVERAGE_INFO_MAP = Arrays.stream(BeverageInfo.values())
                .collect(Collectors.toMap(info -> info.name, Function.identity()));
    }

    private final String name;
    private final int price;

    BeverageInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static BeverageInfo getInfo(String name) {
        return Optional.ofNullable(BEVERAGE_INFO_MAP.get(name))
                .orElseThrow(() -> new IllegalArgumentException("해당 이름에 요리가 존재하지 않습니다."));
    }

    public static boolean isContains(String name) {
        return Arrays.stream(BeverageInfo.values())
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
