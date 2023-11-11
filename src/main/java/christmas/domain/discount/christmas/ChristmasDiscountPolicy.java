package christmas.domain.discount.christmas;

import christmas.domain.discount.DiscountPolicy;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;

import static christmas.domain.discount.christmas.ChristmasDiscount.CHRISTMAS_EVENT;

public class ChristmasDiscountPolicy implements DiscountPolicy {

    private static final int DEFAULT_DISCOUNT = 0;

    @Override
    public int discount(DiscountDto discountDto) {
        LocalDate localDate = discountDto.getLocalDate();

        if (!CHRISTMAS_EVENT.isChristmasEvent(localDate)) {
            return DEFAULT_DISCOUNT;
        }
        return ChristmasDiscount.calculateChristmasDiscountPrice(localDate);
    }
}
