package christmas.service.dto;

import christmas.domain.event.discount.dto.DiscountInfo;

public class DiscountInfoWithGiftItemDTO {
    private final String message;
    private final int discount;

    public DiscountInfoWithGiftItemDTO(String message, int discount) {
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
