package christmas.domain.order.constants;

import static christmas.constants.ErrorPrefix.PREFIX;

public enum OrderMenuErrorMessage {

    NOT_BEVERAGE_ONLY_ERROR_MESSAGE("음료만 주문할 수 없습니다."),
    DUPLICATE_MENU_ERROR_MESSAGE("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String errorMessage;

    OrderMenuErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return PREFIX.getErrorPrefix() + errorMessage;
    }
}

