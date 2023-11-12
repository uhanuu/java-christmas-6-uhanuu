package christmas.domain.event.discount.christmas;

import java.time.LocalDate;

public enum ChristmasDiscount {

    START_DISCOUNT_PRICE(1000),
    CURRENT_PRICE(100);

    private final int discount;
    private static final LocalDate START_DATE = LocalDate.of(2023, 12, 1);
    private static final LocalDate END_DATE = LocalDate.of(2023, 12, 25);

    ChristmasDiscount(int discount) {
        this.discount = discount;
    }

    public static boolean isChristmasEvent(LocalDate localDate) {
        int dayOfMonth = localDate.getDayOfMonth();
        int endDayOfMonth = END_DATE.getDayOfMonth();
        
        return dayOfMonth <= endDayOfMonth;
    }
    
    public static int calculateChristmasDiscountPrice(LocalDate localDate) {
        int daysDifference = localDate.compareTo(START_DATE);

        return (daysDifference * CURRENT_PRICE.discount) + START_DISCOUNT_PRICE.discount;
    }

}
