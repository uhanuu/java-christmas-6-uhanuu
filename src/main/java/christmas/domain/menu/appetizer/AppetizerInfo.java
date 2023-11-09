package christmas.domain.menu.appetizer;

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
}
