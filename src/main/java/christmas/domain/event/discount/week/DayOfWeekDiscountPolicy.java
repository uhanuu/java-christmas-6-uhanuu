package christmas.domain.event.discount.week;

import christmas.domain.event.discount.DiscountPolicy;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.domain.order.OrderItem;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;
import java.util.List;

import static christmas.domain.event.discount.week.WeekdayWeekendDiscount.isEventWeekend;
import static christmas.domain.event.discount.week.WeekdayWeekendDiscount.calculateWeekendMainDiscount;
import static christmas.domain.event.discount.week.WeekdayWeekendDiscount.calculateWeekdayDessertDiscount;

public class DayOfWeekDiscountPolicy implements DiscountPolicy {

    private static final String WEEKDAY_DISCOUNT_FORMAT = "평일 할인 %s:";
    private static final String WEEKEND_DISCOUNT_FORMAT = "주말 할인 %s:";

    @Override
    public DiscountInfo getDiscountInfo(DiscountDto discountDto) {
        LocalDate localDate = discountDto.getLocalDate();
        List<OrderItem> orderItems = discountDto.getOrderItems();

        if (isEventWeekend(localDate)){
            int weekendDiscountPrice = calculateWeekendMainDiscount(orderItems);
            return createInfo(WEEKEND_DISCOUNT_FORMAT, weekendDiscountPrice);
        }
        int weekdayDiscountPrice = calculateWeekdayDessertDiscount(orderItems);
        return createInfo(WEEKDAY_DISCOUNT_FORMAT, weekdayDiscountPrice);
    }

}
