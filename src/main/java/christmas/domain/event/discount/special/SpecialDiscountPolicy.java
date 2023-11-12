package christmas.domain.event.discount.special;

import christmas.domain.event.discount.DiscountPolicy;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;

import static christmas.domain.event.discount.special.SpecialDiscount.isCalendarStarEvent;
import static christmas.domain.event.discount.special.SpecialDiscount.CALENDAR_STAR_EVENT;

public class SpecialDiscountPolicy implements DiscountPolicy {

    private static final String SPECIAL_DISCOUNT_FORMAT = "특별 할인 %s:";
    private static final int DEFAULT_DISCOUNT = 0;

    @Override
    public DiscountInfo discount(DiscountDto discountDto) {
        LocalDate localDate = discountDto.getLocalDate();

        if (isCalendarStarEvent(localDate)) {
            int specialDiscountPrice = CALENDAR_STAR_EVENT.getDiscountPrice();
            return createInfo(SPECIAL_DISCOUNT_FORMAT, specialDiscountPrice);
        }

        return createInfo(SPECIAL_DISCOUNT_FORMAT, DEFAULT_DISCOUNT);
    }
}
