package christmas.domain.discount.week;

import christmas.domain.order.OrderItem;
import christmas.menu.MenuType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

public enum DiscountDayOfWeekEvent {

    WEEKDAY_DESSERT(2_023),
    WEEKEND_MAIN(2_023);

    private static final Month CHRISTMAS_MONTH = Month.DECEMBER;
    private static final int YEAR = 2023;
    private final int discount;

    DiscountDayOfWeekEvent(int discount) {
        this.discount = discount;
    }

    public static boolean isCurrentDate(LocalDate localDate) {
        return isCurrentYear(localDate) && isChristMasMonth(localDate);
    }

    private static boolean isCurrentYear(LocalDate localDate) {
        return localDate.getYear() == YEAR;
    }

    private static boolean isChristMasMonth(LocalDate localDate) {
        return Objects.equals(localDate.getDayOfMonth(), CHRISTMAS_MONTH);
    }

    public static boolean isEventWeekend(LocalDate localDate) {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public static int disCountWeekendMain(List<OrderItem> orderItems) {
        return orderItems.stream()
                .filter(item -> MenuType.isMainMenu(item.getMenuInfo()))
                .mapToInt(item -> item.getQuantity() * WEEKEND_MAIN.discount)
                .sum();
    }

    public static int disCountWeekDayDesert(List<OrderItem> orderItems) {
        return orderItems.stream()
                .filter(item -> MenuType.isDesert(item.getMenuInfo()))
                .mapToInt(item -> item.getQuantity() * WEEKDAY_DESSERT.discount)
                .sum();
    }

}
