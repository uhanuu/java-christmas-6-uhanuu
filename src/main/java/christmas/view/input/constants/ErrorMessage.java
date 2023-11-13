package christmas.view.input.constants;

public enum ErrorMessage {
    PREFIX("[ERROR] "),
    DATE_FORMAT_ERROR_MESSAGE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    NUMBER_FORMAT_ERROR_MESSAGE("숫자만 입력 가능합니다. 다시 입력해 주세요."),
    ORDER_MENU_FORMAT_ERROR_MESSAGE("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX.message + message;
    }
}
