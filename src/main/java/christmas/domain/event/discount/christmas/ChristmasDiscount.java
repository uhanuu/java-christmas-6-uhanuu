package christmas.domain.event.discount.christmas;

import java.time.LocalDate;

public enum ChristmasDiscount {

    CHRISTMAS_EVENT(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 25)),;

    private static final int START_DISCOUNT_PRICE = 1000;
    private static final int CURRENT_PRICE = 100;
    private final LocalDate startDayOfMonth;
    private final LocalDate endDayOfMonth;

    ChristmasDiscount(LocalDate startDayOfMonth, LocalDate endDayOfMonth) {
        this.startDayOfMonth = startDayOfMonth;
        this.endDayOfMonth = endDayOfMonth;
    }

    public static boolean isChristmasEvent(LocalDate localDate) {
        int dayOfMonth = localDate.getDayOfMonth();
        int endDayOfMonth = CHRISTMAS_EVENT.endDayOfMonth.getDayOfMonth();
        
        return dayOfMonth <= endDayOfMonth;
    }
    
    public static int calculateChristmasDiscountPrice(LocalDate localDate) {
        LocalDate startDayOfMont = CHRISTMAS_EVENT.startDayOfMonth;
        int daysDifference = localDate.compareTo(startDayOfMont);

        return (daysDifference * CURRENT_PRICE) + START_DISCOUNT_PRICE;
    }

}
