package christmas.domain.event.discount.special;

import christmas.domain.event.discount.DiscountPolicy;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;

import static christmas.domain.event.discount.special.SpecialDiscount.isCalendarStarEvent;
import static christmas.domain.event.discount.special.SpecialDiscount.CALENDAR_STAR_EVENT;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final int DEFAULT_DISCOUNT = 0;

    @Override
    public int discount(DiscountDto discountDto) {
        LocalDate localDate = discountDto.getLocalDate();
        if (isCalendarStarEvent(localDate)) {
            return CALENDAR_STAR_EVENT.getDiscountPrice();
        }
        return DEFAULT_DISCOUNT;
    }
}
