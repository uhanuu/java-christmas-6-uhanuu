package christmas.domain.discount.week;

import christmas.domain.order.OrderItem;
import christmas.menu.MenuType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public enum WeekdayWeekendDiscount {

    WEEKDAY_EVENT(2_023),
    WEEKEND_EVENT(2_023);
    private final int discount;

    WeekdayWeekendDiscount(int discount) {
        this.discount = discount;
    }

    public static boolean isEventWeekend(LocalDate localDate) {
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek == DayOfWeek.FRIDAY || dayOfWeek == DayOfWeek.SATURDAY;
    }

    public static int calculateWeekendMainDiscount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .filter(item -> MenuType.isMainMenu(item.getMenuInfo()))
                .mapToInt(item -> item.getQuantity() * WEEKEND_EVENT.discount)
                .sum();
    }

    public static int calculateWeekdayDessertDiscount(List<OrderItem> orderItems) {
        return orderItems.stream()
                .filter(item -> MenuType.isDesert(item.getMenuInfo()))
                .mapToInt(item -> item.getQuantity() * WEEKDAY_EVENT.discount)
                .sum();
    }

}
