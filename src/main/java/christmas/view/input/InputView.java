package christmas.view.input;

import christmas.view.input.validator.InputValidator;

import java.time.LocalDate;
import java.util.List;

import static christmas.view.input.constants.InputMessage.*;

public class InputView implements ConsoleInput{

    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    @Override
    public LocalDate requestVisitDate() {
        printVisitDateMessage();
        int monthOfDate = inputValidator.parseInt(readLine());
        return inputValidator.validateDate(monthOfDate);
    }

    private void printVisitDateMessage() {
        System.out.println(START_MESSAGE.getMessage());
        System.out.println(REQUEST_DATE_MESSAGE.getMessage());
    }

    @Override
    public List<String> requestOrderItems() {
        printOrderItemsMessage();
        List<String> requestOrderItems = List.of(readLine().replaceAll(" ","").split(","));
        validateItems(requestOrderItems);
        return requestOrderItems;
    }

    private void printOrderItemsMessage() {
        System.out.println(REQUEST_MENU_AND_QUANTITY_MESSAGE.getMessage());
    }

    private void validateItems(List<String> requestOrderItems) {
        inputValidator.validateOrderItemsForm(requestOrderItems);
        inputValidator.validateOrderItemsSize(requestOrderItems);
    }

}
