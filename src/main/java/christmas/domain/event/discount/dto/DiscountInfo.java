package christmas.domain.event.discount.dto;

public class DiscountInfo {
    private final String message;
    private final int discount;

    public DiscountInfo(String message, int discount) {
        this.message = message;
        this.discount = discount;
    }

    public String getMessage() {
        return message;
    }

    public int getDiscount() {
        return discount;
    }
}
