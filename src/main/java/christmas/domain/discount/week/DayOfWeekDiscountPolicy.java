package christmas.domain.discount.week;

import christmas.controller.dto.ChristMaxOrderDto;
import christmas.domain.discount.DiscountPolicy;
import christmas.domain.order.OrderItem;

import java.time.LocalDate;
import java.util.List;

import static christmas.domain.discount.week.DiscountDayOfWeekEvent.isCurrentDate;
import static christmas.domain.discount.week.DiscountDayOfWeekEvent.isEventWeekend;
import static christmas.domain.discount.week.DiscountDayOfWeekEvent.disCountWeekendMain;
import static christmas.domain.discount.week.DiscountDayOfWeekEvent.disCountWeekDayDesert;

public class DayOfWeekDiscountPolicy implements DiscountPolicy {

    private static final int DEFAULT_DISCOUNT = 0;

    @Override
    public int discount(ChristMaxOrderDto christMaxOrderDto) {
        LocalDate localDate = christMaxOrderDto.getLocalDate();
        if (!isCurrentDate(localDate)) {
            return DEFAULT_DISCOUNT;
        }

        List<OrderItem> orderItems = christMaxOrderDto.getOrderItems();
        if (isEventWeekend(localDate)){
            return disCountWeekendMain(orderItems);
        }
        return disCountWeekDayDesert(orderItems);
    }

}
