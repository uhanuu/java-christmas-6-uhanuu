package christmas.domain.discount;

import christmas.controller.dto.ChristMaxOrderDto;

public interface DiscountPolicy {

    int discount(ChristMaxOrderDto christMaxOrderDto);
}
