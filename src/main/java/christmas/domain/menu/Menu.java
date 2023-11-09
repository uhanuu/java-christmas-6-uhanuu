package christmas.domain.menu;

public abstract class Menu {
    protected final String name;
    protected final int price;
    protected final int quantity;

    public Menu(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return price * quantity;
    }

}
