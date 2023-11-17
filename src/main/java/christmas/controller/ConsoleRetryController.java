package christmas.controller;

import christmas.view.input.ConsoleInput;

import java.util.function.Supplier;

import static christmas.controller.RetryCount.MAX_RETRY_COUNT;

public class ConsoleRetryController {

    protected final ConsoleInput input;

    protected ConsoleRetryController(ConsoleInput input) {
        this.input = input;
    }

    protected  <T> T execution(final Supplier<T> supplier) {
        int retryCount = 0;
        while (RetryCount.isRetry(retryCount)) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                input.printErrorMessage(e);
            }
            retryCount += 1;
        }
        throw new IllegalStateException(MAX_RETRY_COUNT.getErrorMessage());
    }

}
