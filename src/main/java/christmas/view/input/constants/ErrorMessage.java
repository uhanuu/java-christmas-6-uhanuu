package christmas.view.input.constants;

import christmas.constants.ErrorPrefix;

public enum ErrorMessage {
    DATE_FORMAT_ERROR_MESSAGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    ORDER_MENU_FORMAT_ERROR_MESSAGE("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ErrorPrefix.ERROR_PREFIX.getErrorPrefix() + message;
    }
}
