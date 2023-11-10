package christmas.view.input.validator;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern ORDER_ITEMS_PATTERN = Pattern.compile("[가-힣]+-[0-9]{1,2}$");

    private final int YEAR = 2023;
    private final int MONTH = 12;

    public LocalDate validateDate(int monthOfDate) {
        try {
            return LocalDate.of(YEAR, MONTH, monthOfDate);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException(String.format("%d년 %d월은 %d일이 없습니다.", YEAR, MONTH, monthOfDate));
        }
    }

    public int parseInt(String monthOfDate) {
        try {
            return Integer.parseInt(monthOfDate);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    public void validateOrderItems(List<String> requestOrderItems) {
        for (String orderItem : requestOrderItems) {
            Matcher matcher = ORDER_ITEMS_PATTERN.matcher(orderItem);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("주문 상품의 입력이 올바르지 않습니다.");
            }
        }

    }
}
