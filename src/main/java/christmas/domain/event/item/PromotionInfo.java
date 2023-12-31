package christmas.domain.event.item;

public enum PromotionInfo {

    CHAMPAGNE("샴페인", 25_000, 1),
    EMPTY("없음", 0, 0),;

    private static final int MIN_ORDER_AMOUNT_FOR_CHAMPAGNE = 120_000;
    private static final String PROMOTION_EVENT_FORMAT = "증정 이벤트: %s";

    private final String name;
    private final int price;
    private final int quantity;

    PromotionInfo(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public static PromotionInfo getGiftItem(int totalOrderPrice) {
        if (hasGiftEligibility(totalOrderPrice)) {
            return CHAMPAGNE;
        }
        return EMPTY;
    }

    private static boolean hasGiftEligibility(int totalOrderPrice) {
        return totalOrderPrice >= MIN_ORDER_AMOUNT_FOR_CHAMPAGNE;
    }

    public String getPromotionEventNameFormat() {
        return PROMOTION_EVENT_FORMAT;
    }

    public boolean isPresent() {
        return !this.equals(EMPTY);
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
