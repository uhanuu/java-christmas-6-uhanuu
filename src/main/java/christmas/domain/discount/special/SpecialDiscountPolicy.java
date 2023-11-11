package christmas.domain.discount.special;

import christmas.domain.discount.DiscountPolicy;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;

import static christmas.domain.discount.special.SpecialDiscount.EVENT_CALENDAR_STAR;
import static christmas.domain.discount.special.SpecialDiscount.isCalendarStarEvent;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final int DEFAULT_DISCOUNT = 0;

    @Override
    public int discount(DiscountDto discountDto) {
        LocalDate localDate = discountDto.getLocalDate();
        if (isCalendarStarEvent(localDate)) {
            return EVENT_CALENDAR_STAR.getDiscountPrice();
        }
        return DEFAULT_DISCOUNT;
    }
}
