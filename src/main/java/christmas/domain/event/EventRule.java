package christmas.domain.event;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;

public enum EventRule {

    CHRISTMAS_EVENT(2023, Month.DECEMBER, 10000);
    private final int year;
    private final Month eventMonth;
    private final int minimumTotalOrderPrice;

    EventRule(int year, Month eventMonth,int minimumTotalOrderPrice) {
        this.eventMonth = eventMonth;
        this.year = year;
        this.minimumTotalOrderPrice = minimumTotalOrderPrice;
    }

    public static boolean isTotalPriceAboveMinimum(int totalOrderPrice) {
        return totalOrderPrice >= CHRISTMAS_EVENT.minimumTotalOrderPrice;
    }

    public static boolean isCurrentDate(LocalDate localDate) {
        return isCurrentYear(localDate) && isCurrentDateInChristmasMonth(localDate);
    }

    private static boolean isCurrentYear(LocalDate localDate) {
        return localDate.getYear() == CHRISTMAS_EVENT.year;
    }

    private static boolean isCurrentDateInChristmasMonth(LocalDate localDate) {
        return Objects.equals(localDate.getMonth(), CHRISTMAS_EVENT.eventMonth);
    }
}
