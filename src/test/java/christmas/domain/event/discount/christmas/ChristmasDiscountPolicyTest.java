package christmas.domain.event.discount.christmas;

import christmas.domain.event.discount.DiscountPolicy;
import christmas.domain.order.OrderItem;
import christmas.service.dto.DiscountDto;
import christmas.util.ConvertOrderItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChristmasDiscountPolicyTest {

    private DiscountPolicy discountPolicy = new ChristmasDiscountPolicy();

    @Test
    @DisplayName("1,000원으로 시작하여 크리스마스가 다가올수록 날마다 할인 금액이 100원씩 증가한다.")
    public void discount() {
        // given
        LocalDate date = createDate(25);
        DiscountDto requestDto = createRequestDto(date);
        //시작일인 12월 1일에 1,000원, 2일에 1,100원, ..., 25일엔 3,400원 할인
        int result = 3400;
        // when
        int discount = discountPolicy.discount(requestDto);
        // then
        assertThat(discount).isEqualTo(result);
    }

    @Test
    @DisplayName("이벤트 기간이 아닌경우 0원을 반환한다.")
    public void NoDiscount() {
        // given
        LocalDate date = createDate(26);
        DiscountDto requestDto = createRequestDto(date);
        // when
        int discount = discountPolicy.discount(requestDto);
        // then
        assertThat(discount).isZero();
    }

    private DiscountDto createRequestDto(LocalDate localDate) {
        List<OrderItem> orderItem = createOrderItem();
        return new DiscountDto(localDate, orderItem);
    }

    private List<OrderItem> createOrderItem() {
        List<String> orderItemsForm = List.of("아이스크림-5");
        return ConvertOrderItem.getAllMenu(orderItemsForm);
    }

    private LocalDate createDate(int dayOfMonth) {
        return LocalDate.of(2023, 12, dayOfMonth);
    }

}