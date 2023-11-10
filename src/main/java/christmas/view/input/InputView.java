package christmas.view.input;

import christmas.view.input.validator.InputValidator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class InputView implements ConsoleInput{

    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = new InputValidator();
    }

    public LocalDate requestVisitDate() {
        int monthOfDate = inputValidator.parseInt(readLine());
        return inputValidator.validateDate(monthOfDate);
    }

    public List<String> requestOrderItems() {
        List<String> requestOrderItems = List.of(readLine().split(","));
        inputValidator.validateOrderItems(requestOrderItems);
        return requestOrderItems;
    }
}
