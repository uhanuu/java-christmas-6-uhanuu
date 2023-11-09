package christmas.domain.menu.appetizer;

import java.util.Arrays;

public enum AppetizerInfo {

    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000);

    private final String name;
    private final int price;

    AppetizerInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static AppetizerInfo getInfo(String name) {
        return Arrays.stream(AppetizerInfo.values())
                .filter(info -> info.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름에 요리가 존재하지 않습니다."));
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
