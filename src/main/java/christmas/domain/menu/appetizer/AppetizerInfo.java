package christmas.domain.menu.appetizer;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AppetizerInfo {

    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000);

    private static final Map<String, AppetizerInfo> APPETIZER_INFO_MAP;
    static {
        APPETIZER_INFO_MAP = Arrays.stream(AppetizerInfo.values())
                .collect(Collectors.toMap(info -> info.name, Function.identity()));
    }
    private final String name;
    private final int price;

    AppetizerInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static AppetizerInfo getInfo(String name) {
        return Optional.ofNullable(APPETIZER_INFO_MAP.get(name))
                .orElseThrow(() -> new IllegalArgumentException("해당 이름에 요리가 존재하지 않습니다."));
    }

    public static boolean isContains(String name) {
        return Arrays.stream(AppetizerInfo.values())
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
