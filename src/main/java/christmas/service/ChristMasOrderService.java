package christmas.service;

import christmas.controller.dto.ChristMaxOrderDto;
import christmas.domain.discount.week.DayOfWeekDiscountPolicy;

public class ChristMasOrderService {

    private final DayOfWeekDiscountPolicy discountPolicy = new DayOfWeekDiscountPolicy();

    public int christMaxDiscountPrice(ChristMaxOrderDto christMaxOrderDto) {
        int discount = discountPolicy.discount(christMaxOrderDto);

        return 0;
    }

}
