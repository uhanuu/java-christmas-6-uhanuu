package christmas.domain.event.discount;

import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.service.dto.DiscountDto;

public interface DiscountPolicy {

    DiscountInfo discount(DiscountDto discountDto);

    default DiscountInfo createInfo(String message, int discount) {
        return new DiscountInfo(message, discount);
    }
}
