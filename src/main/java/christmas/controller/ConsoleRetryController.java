package christmas.controller;

import christmas.view.input.ConsoleInput;

import java.util.function.Supplier;

public class ConsoleRetryController {

    protected final ConsoleInput input;

    protected ConsoleRetryController(ConsoleInput input) {
        this.input = input;
    }

    protected  <T> T run(final Supplier<T> supplier) {
        int retryCount = 0;
        while (RetryCount.isRetry(retryCount)) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                input.printErrorMessage(e);
            }
            retryCount += 1;
        }
        throw new IllegalStateException(RetryCount.MAX_RETRY_COUNT.getErrorMessage());
    }

}
