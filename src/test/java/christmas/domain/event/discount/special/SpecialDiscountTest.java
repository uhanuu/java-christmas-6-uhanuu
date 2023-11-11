package christmas.domain.event.discount.special;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class SpecialDiscountTest {

    @ParameterizedTest(name = "{0}일은 event 날이다.")
    @DisplayName("12월 매주 일요일과 25일은 특별 할인이다.")
    @ValueSource(ints = {3, 10, 17, 24, 31, 25})
    public void isCalendarStarEvent(int dayOfMonth) {
        //given
        LocalDate date = createDate(dayOfMonth);
        // when
        boolean isCalendarStarEvent = SpecialDiscount.isCalendarStarEvent(date);
        // then
        assertThat(isCalendarStarEvent).isTrue();
    }

    @ParameterizedTest(name = "{0}일은 event 날이 아니다.")
    @DisplayName("12월 매주 일요일과 25일을 제외한 날짜는 이벤트 기간이 아니다.")
    @ValueSource(ints = {4, 11, 18, 26, 29, 30})
    public void isCalendarStarEventFalse(int dayOfMonth) {
        //given
        LocalDate date = createDate(dayOfMonth);
        // when
        boolean isCalendarStarEvent = SpecialDiscount.isCalendarStarEvent(date);
        // then
        assertThat(isCalendarStarEvent).isFalse();
    }

    private static LocalDate createDate(int dayOfMonth) {
        return LocalDate.of(2023, 12, dayOfMonth);
    }
}