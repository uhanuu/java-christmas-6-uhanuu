package christmas.domain.event;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public enum EventRule {

    CHRISTMAS_EVENT(2023, Month.DECEMBER, 10000);
    private final int year;
    private final Month eventMonth;
    private final int minimumTotalPrice;

    EventRule(int year, Month eventMonth,int minimumTotalPrice) {
        this.eventMonth = eventMonth;
        this.year = year;
        this.minimumTotalPrice = minimumTotalPrice;
    }

    public static boolean isTotalPriceAboveMinimum(int totalPrice) {
        return totalPrice >= CHRISTMAS_EVENT.minimumTotalPrice;
    }

    public static boolean isCurrentDate(LocalDate localDate) {
        return isCurrentYear(localDate) && isCurrentDateInChristmasMonth(localDate);
    }

    private static boolean isCurrentYear(LocalDate localDate) {
        return localDate.getYear() == CHRISTMAS_EVENT.year;
    }

    private static boolean isCurrentDateInChristmasMonth(LocalDate localDate) {
        return Objects.equals(localDate.getDayOfMonth(), CHRISTMAS_EVENT.eventMonth);
    }
}
