package christmas.domain.event.item;

public class GiftItem {

    private final String name;
    private final int price;
    private final int quantity;

    private GiftItem(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static GiftItem of(PromotionInfo promotionInfo) {
        return new GiftItem(
                promotionInfo.getName(),
                promotionInfo.getPrice(),
                promotionInfo.getQuantity());
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}
