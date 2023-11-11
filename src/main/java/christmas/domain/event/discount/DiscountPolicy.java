package christmas.domain.event.discount;

import christmas.service.dto.DiscountDto;

public interface DiscountPolicy {

    int discount(DiscountDto discountDto);
}
