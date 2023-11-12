package christmas.domain.event.discount.christmas;

import christmas.domain.event.discount.DiscountPolicy;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;

public class ChristmasDiscountPolicy implements DiscountPolicy {

    private static final String CHRISTMAS_DISCOUNT_FORMAT = "크리스마스 디데이 할인: %s";
    private static final int DEFAULT_DISCOUNT = 0;

    @Override
    public DiscountInfo getDiscountInfo(DiscountDto discountDto) {
        LocalDate localDate = discountDto.getLocalDate();

        if (!ChristmasDiscount.isChristmasEvent(localDate)) {
            return createInfo(CHRISTMAS_DISCOUNT_FORMAT, DEFAULT_DISCOUNT);
        }

        int christmasDiscountPrice = ChristmasDiscount.calculateChristmasDiscountPrice(localDate);
        return createInfo(CHRISTMAS_DISCOUNT_FORMAT, christmasDiscountPrice);
    }

}
