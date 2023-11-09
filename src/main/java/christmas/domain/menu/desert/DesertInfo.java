package christmas.domain.menu.desert;

import christmas.domain.menu.beverage.BeverageInfo;

import java.util.Arrays;

public enum DesertInfo {

    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000);

    private final String name;
    private final int price;

    DesertInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static DesertInfo getInfo(String name) {
        return Arrays.stream(DesertInfo.values())
                .filter(info -> info.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름에 요리가 존재하지 않습니다."));
    }

    public static boolean isContains(String name) {
        return Arrays.stream(DesertInfo.values())
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
