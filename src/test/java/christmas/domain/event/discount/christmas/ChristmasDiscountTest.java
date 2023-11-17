package christmas.domain.event.discount.christmas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDiscountTest {

    @Test
    @DisplayName("크리스마스 이벤트의 기간은 2023년 12월1일 ~ 2023년 12월25일까지 진행한다.")
    public void isChristmasEventTrue() {
        // given
        LocalDate date = createDate(25);
        // when
        boolean isChristmasEvent = ChristmasDiscount.isChristmasEvent(date);
        // then
        assertThat(isChristmasEvent).isTrue();
    }

    @Test
    @DisplayName("크리스마스 이벤트의 기간이 아니면 false를 반환한다.")
    public void isChristmasEventFalse() {
        // given
        LocalDate date = createDate(26);
        // when
        boolean isChristmasEvent = ChristmasDiscount.isChristmasEvent(date);
        // then
        assertThat(isChristmasEvent).isFalse();
    }
    
    @Test
    @DisplayName("1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가한다.")
    public void calculateChristmasDiscountPrice() {
        // given
        LocalDate date = createDate(25);
        //시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인
        int result = 3400;
        // when
        int discountPrice = ChristmasDiscount.calculateChristmasDiscountPrice(date);
        // then
        assertThat(discountPrice).isEqualTo(result);
    }

    private static LocalDate createDate(int dayOfMonth) {
        return LocalDate.of(2023, 12, dayOfMonth);
    }

}