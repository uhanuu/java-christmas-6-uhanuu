package christmas.domain.discount.week;

import christmas.domain.discount.DiscountPolicy;
import christmas.domain.order.OrderItem;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;
import java.util.List;

import static christmas.domain.discount.week.WeekdayWeekendDiscount.isEventWeekend;
import static christmas.domain.discount.week.WeekdayWeekendDiscount.calculateWeekendMainDiscount;
import static christmas.domain.discount.week.WeekdayWeekendDiscount.calculateWeekdayDessertDiscount;

public class DayOfWeekDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(DiscountDto discountDto) {
        LocalDate localDate = discountDto.getLocalDate();
        List<OrderItem> orderItems = discountDto.getOrderItems();

        if (isEventWeekend(localDate)){
            return calculateWeekendMainDiscount(orderItems);
        }
        return calculateWeekdayDessertDiscount(orderItems);
    }

}
