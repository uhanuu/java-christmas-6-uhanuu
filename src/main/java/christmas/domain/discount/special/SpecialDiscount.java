package christmas.domain.discount.special;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum SpecialDiscount {
    CALENDAR_STAR_EVENT(1000, 25);

    private final int discountPrice;
    private final int christmasDay;

    SpecialDiscount(int discountPrice, int christmasDay) {
        this.discountPrice = discountPrice;
        this.christmasDay = christmasDay;
    }

    public static boolean isCalendarStarEvent(LocalDate localDate) {
        return isSunday(localDate) || isChristmasDay(localDate);
    }

    private static boolean isSunday(LocalDate localDate) {
        return localDate.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    private static boolean isChristmasDay(LocalDate localDate) {
        return localDate.getDayOfMonth() == CALENDAR_STAR_EVENT.christmasDay;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }
}
