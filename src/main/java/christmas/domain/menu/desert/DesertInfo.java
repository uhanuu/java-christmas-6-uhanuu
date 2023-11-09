package christmas.domain.menu.desert;

public enum DesertInfo {

    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000);

    private final String name;
    private final int price;

    DesertInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
