package christmas.domain.event.discount.week;

import christmas.domain.event.discount.DiscountPolicy;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.domain.order.OrderItem;
import christmas.service.dto.DiscountDto;
import christmas.util.ConvertOrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DayOfWeekDiscountPolicyTest {

    private DiscountPolicy discountPolicy = new DayOfWeekDiscountPolicy();

    @ParameterizedTest(name = "{0}일은 주말 event로 메인음식들의 수량마다 2023원이 할인된다.")
    @DisplayName("금요일과 토요일은 주말이벤트가 적용된다.")
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    public void isCalendarStarEventTrue(int dayOfMonth) {
        //given
        List<String> orderMainItemsForm = List.of("티본스테이크-3", "바비큐립-3", "제로콜라-2");
        List<OrderItem> orderItems = createOrderItems(orderMainItemsForm);
        LocalDate date = createDate(dayOfMonth);
        DiscountDto requestDto = createRequestDto(date, orderItems);
        int result = 2023 * 6;
        // when
        DiscountInfo discountInfo = discountPolicy.getDiscountInfo(requestDto);
        // then
        assertThat(result).isEqualTo(discountInfo.getDiscount());
    }

    @ParameterizedTest(name = "{0}일은 주말이 아닌 평일로 디저트음식들이 수량마다 2023원이 할인된다.")
    @DisplayName("금요일과 토요일을 제외하면 평일이벤트가 적용된다.")
    @ValueSource(ints = {3, 4, 10, 11, 17, 18, 24, 25})
    public void isCalendarStarEventFalse(int dayOfMonth) {
        //given
        List<String> orderMainItemsForm = List.of("초코케이크-4", "아이스크림-4", "제로콜라-2");
        List<OrderItem> orderItems = createOrderItems(orderMainItemsForm);
        LocalDate date = createDate(dayOfMonth);
        DiscountDto requestDto = createRequestDto(date, orderItems);
        int result = 2023 * 8;
        // when
        DiscountInfo discountInfo = discountPolicy.getDiscountInfo(requestDto);
        // then
        assertThat(result).isEqualTo(discountInfo.getDiscount());
    }

    private List<OrderItem> createOrderItems(List<String> orderMainItems) {
        return ConvertOrderItem.getAllMenu(orderMainItems);
    }

    private DiscountDto createRequestDto(LocalDate localDate, List<OrderItem> orderItems) {
        return new DiscountDto(localDate, orderItems);
    }

    private LocalDate createDate(int dayOfMonth) {
        return LocalDate.of(2023, 12, dayOfMonth);
    }

}