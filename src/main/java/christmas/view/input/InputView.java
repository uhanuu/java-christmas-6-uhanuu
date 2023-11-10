package christmas.view.input;

import christmas.view.input.validator.InputValidator;

import java.time.LocalDate;
import java.util.List;

public class InputView implements ConsoleInput{

    private final InputValidator inputValidator;

    public InputView() {
        this.inputValidator = new InputValidator();
    }

    public LocalDate requestVisitDate() {
        int monthOfDate = inputValidator.parseInt(readLine());
        return inputValidator.validateDate(monthOfDate);
    }

    public List<String> requestOrderItems() {
        List<String> requestOrderItems = List.of(readLine().trim().split(","));
        validateItems(requestOrderItems);
        return requestOrderItems;
    }

    private void validateItems(List<String> requestOrderItems) {
        inputValidator.validateOrderItemsForm(requestOrderItems);
        inputValidator.validateOrderItemsSize(requestOrderItems);
    }
}
