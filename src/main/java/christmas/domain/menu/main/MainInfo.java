package christmas.domain.menu.main;

public enum MainInfo {

    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000);

    private final String name;
    private final int price;

    MainInfo(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
