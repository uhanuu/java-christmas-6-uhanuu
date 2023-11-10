package christmas.view.input;

import christmas.view.input.message.InputMessage;
import christmas.view.input.validator.InputValidator;

import java.time.LocalDate;
import java.util.List;

public class InputView implements ConsoleInput{

    private final InputValidator inputValidator;

    public InputView() {
        this.inputValidator = new InputValidator();
    }

    public LocalDate requestVisitDate() {
        printVisitDateMessage();
        int monthOfDate = inputValidator.parseInt(readLine());
        return inputValidator.validateDate(monthOfDate);
    }

    private void printVisitDateMessage() {
        System.out.println(InputMessage.START_MESSAGE.getMessage());
        System.out.println(InputMessage.REQUEST_DATE_MESSAGE.getMessage());
    }

    public List<String> requestOrderItems() {
        printOrderItemsMessage();
        List<String> requestOrderItems = List.of(readLine().trim().split(","));
        validateItems(requestOrderItems);
        return requestOrderItems;
    }

    private void printOrderItemsMessage() {
        System.out.println(InputMessage.REQUEST_MENU_AND_QUANTITY_MESSAGE.getMessage());
    }

    private void validateItems(List<String> requestOrderItems) {
        inputValidator.validateOrderItemsForm(requestOrderItems);
        inputValidator.validateOrderItemsSize(requestOrderItems);
    }
}
