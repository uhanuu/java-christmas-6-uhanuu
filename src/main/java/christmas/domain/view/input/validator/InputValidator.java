package christmas.domain.view.input.validator;

import java.time.DateTimeException;
import java.time.LocalDate;

public class InputValidator {

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
}
