package christmas.domain.event;

import java.util.Arrays;

public enum EventBadge {

    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String name;
    private final int threshold;

    EventBadge(String name, int threshold) {
        this.name = name;
        this.threshold = threshold;
    }

    public static EventBadge getBadgeByTotalEventPrice(int totalEventPrice) {
        return Arrays.stream(EventBadge.values())
                .filter(badge -> badge.threshold <= totalEventPrice)
                .reduce((first, second) -> second)
                .orElse(EventBadge.NONE);
    }

    public String getName() {
        return name;
    }

}
