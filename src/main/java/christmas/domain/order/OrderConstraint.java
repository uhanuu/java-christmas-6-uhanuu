package christmas.domain.order;

import christmas.constants.ErrorPrefix;

public enum OrderConstraint {

    MIN_MENU_COUNT(1, "유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MAX_MENU_COUNT(20, "주문 상품은 최대 20개 입니다.");

    private static final String NOT_BEVERAGE_ONLY = "음료만 주문할 수 없습니다.";
    private final int limit;
    private final String errorMessage;


    OrderConstraint(int limit, String errorMessage) {
        this.limit = limit;
        this.errorMessage = errorMessage;
    }

    public static void validateNotBeverageOnly(int itemSize, int beverageItemCount) {
        if (itemSize == beverageItemCount) {
            throw new IllegalArgumentException(ErrorPrefix.ERROR_PREFIX.getErrorPrefix() + NOT_BEVERAGE_ONLY);
        }

    }

    public int getLimit() {
        return limit;
    }

    public String getErrorMessage() {
        return ErrorPrefix.ERROR_PREFIX.getErrorPrefix() + errorMessage;
    }
}
