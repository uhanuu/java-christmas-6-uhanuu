package christmas.domain.event.discount;

import christmas.domain.event.discount.christmas.ChristmasDiscountPolicy;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.domain.event.discount.special.SpecialDiscountPolicy;
import christmas.domain.event.discount.week.DayOfWeekDiscountPolicy;
import christmas.domain.order.OrderItem;
import christmas.service.dto.DiscountDto;
import christmas.service.dto.DiscountItemDto;
import christmas.util.ConvertOrderItem;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountEventManagerTest {

    private final DiscountEventManager discountEventManager = new DiscountEventManager(
            List.of(new SpecialDiscountPolicy(), new ChristmasDiscountPolicy(), new DayOfWeekDiscountPolicy()));

    @Test
    @DisplayName("주문 내역을 통해서 이벤트 이름과 할인된 금액을 얻을 수 있다.")
    public void getDiscountInfos() {
        // given
        LocalDate date = createDate(3);
        DiscountDto requestDto = createRequestDto(date, List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        // when
        List<DiscountInfo> discountInfos = discountEventManager.getDiscountInfos(requestDto);
        // then
        assertThat(discountInfos).hasSize(3)
                .extracting("message", "discount")
                .contains(
                        Tuple.tuple("크리스마스 디데이 할인: %s", 1200),
                        Tuple.tuple("평일 할인: %s", 4046),
                        Tuple.tuple("특별 할인: %s", 1000));
    }

    @Test
    @DisplayName("2023년 12월이 아니면 이벤트 적용이 안된다.")
    public void getDiscountInfosByDate() {
        // given
        LocalDate date = LocalDate.of(2022, 12, 3);
        DiscountDto requestDto = createRequestDto(date, List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        // when
        List<DiscountInfo> discountInfos = discountEventManager.getDiscountInfos(requestDto);
        // then
        assertThat(discountInfos).isEmpty();
    }

    private DiscountDto createRequestDto(LocalDate localDate, List<String> orderRequest) {
        List<DiscountItemDto> orderItem = createOrderItem(orderRequest);
        return new DiscountDto(localDate, orderItem, 1);
    }

    private List<DiscountItemDto> createOrderItem(List<String> orderRequest) {
        List<OrderItem> allMenu = ConvertOrderItem.getAllMenu(orderRequest);

        return allMenu.stream()
                .map(DiscountItemDto::of)
                .toList();
    }

    private LocalDate createDate(int dayOfMonth) {
        return LocalDate.of(2023, 12, dayOfMonth);
    }

}