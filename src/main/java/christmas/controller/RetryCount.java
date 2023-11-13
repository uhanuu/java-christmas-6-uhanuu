package christmas.controller;

import christmas.constants.ErrorPrefix;

public enum RetryCount {
    MAX_RETRY_COUNT(5, "재시도 횟수를 초과했습니다."),;

    private final int count;
    private final String errorMessage;

    RetryCount(int count, String errorMessage) {
        this.count = count;
        this.errorMessage = errorMessage;
    }

    public static boolean isRetry(int retryCount) {
        return retryCount < MAX_RETRY_COUNT.count;
    }

    public String getErrorMessage() {
        return ErrorPrefix.ERROR_PREFIX.getErrorPrefix() + errorMessage;
    }
}
