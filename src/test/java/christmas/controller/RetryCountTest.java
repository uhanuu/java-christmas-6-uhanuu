package christmas.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class RetryCountTest {

    @ParameterizedTest
    @DisplayName("재시도 횟수가 5회 이하면 true를 반환한다.")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    public void isRetry(int retryCount) {
        // when
        boolean isRetry = RetryCount.isRetry(retryCount);
        // then
        assertTrue(isRetry);
    }

    @Test
    @DisplayName("재시도 횟수가 5회 초과면 false를 반환한다.")
    public void isRetryFalse() {
        //given
        int retryCount = 6;
        // when
        boolean isRetry = RetryCount.isRetry(retryCount);
        // then
        assertFalse(isRetry);
    }

}