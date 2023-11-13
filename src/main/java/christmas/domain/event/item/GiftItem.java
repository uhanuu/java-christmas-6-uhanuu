package christmas.domain.event.item;

public class GiftItem {

    private final String message;
    private final String name;
    private final int price;
    private final int quantity;

    public GiftItem(String message, String name, int price, int quantity) {
        this.message = message;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static GiftItem of(PromotionInfo promotionInfo) {
        return new GiftItem(
                promotionInfo.getPromotionEventNameFormat(),
                promotionInfo.getName(),
                promotionInfo.getPrice(),
                promotionInfo.getQuantity());
    }

    public String getMessage() {
        return message;
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
