package christmas.domain.menu.beverage;

public enum BeverageInfo {

    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private final String name;
    private final int price;

    BeverageInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
