package christmas.view.input.validator;

import christmas.view.input.constants.ErrorMessage;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    //0-9로 바꾸고 다음에 숫자 변경할 때 0이면 예외처리하기
    private static final Pattern ORDER_ITEMS_PATTERN = Pattern.compile("([가-힣]+)-([1-9][0-9]?)");

    private final int YEAR = 2023;
    private final int MONTH = 12;

    public LocalDate validateDate(int monthOfDate) {
        try {
            return LocalDate.of(YEAR, MONTH, monthOfDate);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(ErrorMessage.DATE_FORMAT_ERROR_MESSAGE.getMessage());
        }
    }

    public int parseInt(String monthOfDate) {
        try {
            return Integer.parseInt(monthOfDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.DATE_FORMAT_ERROR_MESSAGE.getMessage());
        }
    }

    public void validateOrderItemsForm(List<String> requestOrderItems) {
        for (String orderItem : requestOrderItems) {
            Matcher matcher = ORDER_ITEMS_PATTERN.matcher(orderItem);
            if (!matcher.matches()) {
                throw new IllegalArgumentException(ErrorMessage.ORDER_MENU_FORMAT_ERROR_MESSAGE.getMessage());
            }
        }
    }

    public void validateOrderItemsSize(List<String> requestOrderItems) {
        if (requestOrderItems.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.ORDER_MENU_FORMAT_ERROR_MESSAGE.getMessage());
        }
    }
}
