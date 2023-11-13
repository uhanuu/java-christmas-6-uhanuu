package christmas.domain.event.discount.week;

import christmas.domain.order.OrderItem;
import christmas.service.dto.DiscountItemDto;
import christmas.util.ConvertOrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WeekdayWeekendDiscountTest {

    @ParameterizedTest(name = "{0}일은 주말 event 날아다.")
    @DisplayName("금요일과 토요일은 주말이벤트가 적용된다.")
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    public void isCalendarStarEventTrue(int dayOfMonth) {
        //given
        LocalDate date = createDate(dayOfMonth);
        // when
        boolean isEventWeekend = WeekdayWeekendDiscount.isEventWeekend(date);
        // then
        assertThat(isEventWeekend).isTrue();
    }

    @ParameterizedTest(name = "{0}일은 주말 event 날이 아니다.")
    @DisplayName("금요일과 토요일을 제외하면 평일이기 떄문에 주말 이벤트가 아니다.")
    @ValueSource(ints = {3, 4, 10, 11, 17, 18, 24, 25})
    public void isCalendarStarEventFalse(int dayOfMonth) {
        //given
        LocalDate date = createDate(dayOfMonth);
        // when
        boolean isEventWeekend = WeekdayWeekendDiscount.isEventWeekend(date);
        // then
        assertThat(isEventWeekend).isFalse();
    }

    @Test
    @DisplayName("메인 음식의 수량만큼 2023원씩 할인이 들어간다.")
    public void calculateWeekendMainDiscount() {
        // given
        List<String> orderMainItemsForm = List.of("티본스테이크-1", "바비큐립-1", "제로콜라-2");
        List<DiscountItemDto> mainOrderItems = createOrderItems(orderMainItemsForm);
        //메인 음식 2개
        int result = 2023 * 2;
        // when
        int discount = WeekdayWeekendDiscount.calculateWeekendMainDiscount(mainOrderItems);
        // then
        assertThat(discount).isEqualTo(result);
    }

    @Test
    @DisplayName("디저트 음식의 수량만큼 2023원씩 할인이 들어간다.")
    public void calculateWeekendDesertDiscount() {
        // given
        List<String> orderMainItemsForm = List.of("초코케이크-1", "아이스크림-1", "제로콜라-2");
        List<DiscountItemDto> mainOrderItems = createOrderItems(orderMainItemsForm);
        //메인 음식 2개
        int result = 2023 * 2;
        // when
        int discount = WeekdayWeekendDiscount.calculateWeekdayDessertDiscount(mainOrderItems);
        // then
        assertThat(discount).isEqualTo(result);
    }

    private LocalDate createDate(int dayOfMonth) {
        return LocalDate.of(2023, 12, dayOfMonth);
    }

    private List<DiscountItemDto> createOrderItems(List<String> orderMainItems) {
        List<OrderItem> allMenu = ConvertOrderItem.getAllMenu(orderMainItems);
        return allMenu.stream()
                .map(DiscountItemDto::of)
                .toList();
    }

}