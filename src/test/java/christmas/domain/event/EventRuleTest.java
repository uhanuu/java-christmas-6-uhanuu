package christmas.domain.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EventRuleTest {

    @Test
    @DisplayName("주문 금액이 10000원이 넘으면 true를 반환한다.")
    public void isTotalPriceAboveMinimumTrue() {
        // given
        int totalOrderPrice = 10000;
        // when
        boolean isTotalPriceAboveMinimum = EventRule.isTotalPriceAboveMinimum(totalOrderPrice);
        // then
        assertTrue(isTotalPriceAboveMinimum);
    }

    @Test
    @DisplayName("주문 금액이 10000원이 넘지 못하면 false를 반환한다.")
    public void isTotalPriceAboveMinimum() {
        // given
        int totalOrderPrice = 9999;
        // when
        boolean isTotalPriceAboveMinimum = EventRule.isTotalPriceAboveMinimum(totalOrderPrice);
        // then
        assertFalse(isTotalPriceAboveMinimum);
    }

    @ParameterizedTest(name = "2023년 12월 {0}일은 이벤트 기간이다.")
    @DisplayName("2023년 12월이면 true를 반환한다.")
    @ValueSource(ints = {1, 2, 3, 12, 13, 21, 30, 31})
    public void isCurrentDateTrue(int dayOfMonth) {
        // given
        LocalDate date = LocalDate.of(2023, 12, dayOfMonth);
        // when
        boolean isCurrentDate = EventRule.isCurrentDate(date);
        // then
        assertTrue(isCurrentDate);
    }

    @ParameterizedTest(name = "2023년 {0}월은 이벤트 기간이 아니다.")
    @DisplayName("2023년 12월이 아니면 false를 반환한다.")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})
    public void isCurrentDateFalseMonth(int month) {
        // given
        LocalDate date = LocalDate.of(2023, month, 1);
        // when
        boolean isCurrentDate = EventRule.isCurrentDate(date);
        // then
        assertFalse(isCurrentDate);
    }

    @ParameterizedTest(name = "{0}년 12월 1일은 이벤트 기간이 아니다.")
    @DisplayName("2023년 12월이 아니면 false를 반환한다.")
    @ValueSource(ints = {2000, 2010, 2020, 2021, 2022})
    public void isCurrentDateFalseYear(int year) {
        // given
        LocalDate date = LocalDate.of(year, 12, 1);
        // when
        boolean isCurrentDate = EventRule.isCurrentDate(date);
        // then
        assertFalse(isCurrentDate);
    }
}