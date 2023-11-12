package christmas.domain.event.discount.special;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum SpecialDiscount {
    CALENDAR_STAR_EVENT(1000);

    private static final int CHRISTMAS_DAY_OF_MONTH = 25;
    private final int discountPrice;

    SpecialDiscount(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public static boolean isCalendarStarEvent(LocalDate localDate) {
        return isSunday(localDate) || isChristmasDay(localDate);
    }

    private static boolean isSunday(LocalDate localDate) {
        return localDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private static boolean isChristmasDay(LocalDate localDate) {
        return localDate.getDayOfMonth() == CHRISTMAS_DAY_OF_MONTH;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
