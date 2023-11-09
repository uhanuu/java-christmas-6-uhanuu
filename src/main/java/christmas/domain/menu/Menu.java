package christmas.domain.menu;

public abstract class Menu {

    protected final String name;
    protected final int price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

}
