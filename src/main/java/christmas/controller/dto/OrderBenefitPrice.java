package christmas.controller.dto;

public class OrderBenefitPrice {

    private final int discountPrice;
    private final int giftItemPrice;

    public OrderBenefitPrice(int discountPrice, int giftItemPrice) {
        this.discountPrice = discountPrice;
        this.giftItemPrice = giftItemPrice;
    }

    public int getTotalBenefitPrice() {
        return discountPrice + giftItemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

}
