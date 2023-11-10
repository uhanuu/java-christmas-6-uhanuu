package christmas.domain.view.input;

import christmas.domain.view.input.validator.InputValidator;

import java.time.LocalDate;

public class InputView implements ConsoleInput{

    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = new InputValidator();
    }

    public LocalDate requestVisitDate() {
        int monthOfDate = inputValidator.parseInt(readLine());
        return inputValidator.validateDate(monthOfDate);
    }
}
