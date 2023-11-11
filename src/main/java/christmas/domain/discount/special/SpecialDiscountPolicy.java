package christmas.domain.discount.special;

import christmas.domain.discount.DiscountPolicy;
import christmas.service.dto.DiscountDto;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final int DEFAULT_DISCOUNT = 0;

    @Override
    public int discount(DiscountDto discountDto) {

        return 0;
    }
}
