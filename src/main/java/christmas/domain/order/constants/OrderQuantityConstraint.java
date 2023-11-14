package christmas.domain.order.constants;

import static christmas.constants.ErrorPrefix.PREFIX;

public enum OrderQuantityConstraint {

    MIN_MENU_COUNT(1, "유효하지 않은 주문입니다. 다시 입력해 주세요."),
    MAX_MENU_COUNT(20, "주문 상품은 최대 %d개 입니다.");

    private final int limit;
    private final String errorMessage;

    OrderQuantityConstraint(int limit, String errorMessage) {
        this.limit = limit;
        this.errorMessage = errorMessage;
    }

    public int getLimit() {
        return limit;
    }

    public String getErrorMessage() {
        return PREFIX.getErrorPrefix() + errorMessage;
    }
}
