package christmas.domain.event.discount.special;

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

class SpecialDiscountPolicyTest {

    private DiscountPolicy discountPolicy = new SpecialDiscountPolicy();

    @ParameterizedTest(name = "{0}일은 event 날이다.")
    @DisplayName("12월 매주 일요일과 25일은 1000원 특별 할인된다.")
    @ValueSource(ints = {3, 10, 17, 24, 31, 25})
    public void SpecialDiscountPolicy(int dayOfMonth) {
        //given
        int result = 1000;
        LocalDate date = createDate(dayOfMonth);
        DiscountDto requestDto = createRequestDto(date);
        // when
        DiscountInfo discountInfo = discountPolicy.getDiscountInfo(requestDto);
        // then
        assertThat(discountInfo.getDiscount()).isEqualTo(result);
    }

    @ParameterizedTest(name = "{0}일은 event 날이 아니다.")
    @DisplayName("12월 매주 일요일과 25일을 제외한 날짜는 이벤트 기간이 아니다.")
    @ValueSource(ints = {4, 11, 18, 26, 29, 30})
    public void SpecialDiscountPolicyNoEvent(int dayOfMonth) {
        //given
        int result = 0;
        LocalDate date = createDate(dayOfMonth);
        DiscountDto requestDto = createRequestDto(date);
        // when
        DiscountInfo discountInfo = discountPolicy.getDiscountInfo(requestDto);
        // then
        assertThat(discountInfo.getDiscount()).isEqualTo(result);
    }

    private DiscountDto createRequestDto(LocalDate localDate) {
        List<OrderItem> orderItem = createOrderItem();
        return new DiscountDto(localDate, orderItem, 1);
    }

    private List<OrderItem> createOrderItem() {
        List<String> orderItemsForm = List.of("아이스크림-5");
        return ConvertOrderItem.getAllMenu(orderItemsForm);
    }

    private LocalDate createDate(int dayOfMonth) {
        return LocalDate.of(2023, 12, dayOfMonth);
    }

}