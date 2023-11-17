package christmas.domain.event.item;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftItem giftItem = (GiftItem) o;
        return price == giftItem.price && quantity == giftItem.quantity && Objects.equals(message, giftItem.message) && Objects.equals(name, giftItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, name, price, quantity);
    }
}
