package christmas.domain.event.discount.christmas;

import christmas.domain.event.discount.DiscountPolicy;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;

public class ChristmasDiscountPolicy implements DiscountPolicy {

    private static final int DEFAULT_DISCOUNT = 0;

    @Override
    public int discount(DiscountDto discountDto) {
        LocalDate localDate = discountDto.getLocalDate();

        if (!ChristmasDiscount.CHRISTMAS_EVENT.isChristmasEvent(localDate)) {
            return DEFAULT_DISCOUNT;
        }
        return ChristmasDiscount.calculateChristmasDiscountPrice(localDate);
    }
}
