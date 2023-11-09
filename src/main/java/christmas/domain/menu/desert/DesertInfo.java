package christmas.domain.menu.desert;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum DesertInfo {

    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000);

    private static final Map<String, DesertInfo> DESERT_INFO_MAP;
    static {
        DESERT_INFO_MAP = Arrays.stream(DesertInfo.values())
                .collect(Collectors.toMap(info -> info.name, Function.identity()));
    }

    private final String name;
    private final int price;

    DesertInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static DesertInfo getInfo(String name) {
        return Optional.ofNullable(DESERT_INFO_MAP.get(name))
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
