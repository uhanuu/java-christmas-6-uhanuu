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
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DiscountEventManagerTest {

    private final DiscountEventManager discountEventManager = new DiscountEventManager(
            List.of(new SpecialDiscountPolicy(), new ChristmasDiscountPolicy(), new DayOfWeekDiscountPolicy()));

    @Test
    @DisplayName("주문 내역을 통해서 이벤트 이름과 할인된 금액을 얻을 수 있다.")
    public void getDiscountInfos() {
        // given
        LocalDate date = createDate(26);
        DiscountDto requestDto = createRequestDto(date, List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        // when
        List<DiscountInfo> discountInfos = discountEventManager.getDiscountInfos(requestDto);
        // then
        assertThat(discountInfos).hasSize(3)
                .extracting("message", "discount")
                .contains(
                        Tuple.tuple("크리스마스 디데이 할인: %s", 0),
                        Tuple.tuple("평일 할인: %s", 4046),
                        Tuple.tuple("특별 할인: %s", 0));
    }

    @DisplayName("2023년 12월이 아니면 이벤트 적용이 안된다.")
    @TestFactory
    Collection<DynamicTest> getDiscountInfosByDate() {
        // given
        return List.of(
                DynamicTest.dynamicTest("연도가 다르면 이벤트 적용이 안된다.", () -> {
                    // given
                    LocalDate date1 = LocalDate.of(2022, 12, 3);
                    DiscountDto requestDto = createRequestDto(date1, List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
                    // when
                    List<DiscountInfo> discountInfos = discountEventManager.getDiscountInfos(requestDto);
                    // then
                    assertThat(discountInfos).isEmpty();
                }),
                DynamicTest.dynamicTest("월이 다르면 이벤트 적용이 안된다.", () -> {
                    // given
                    LocalDate date2 = LocalDate.of(2023, 11, 3);
                    DiscountDto requestDto = createRequestDto(date2, List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
                    // when
                    List<DiscountInfo> discountInfos = discountEventManager.getDiscountInfos(requestDto);
                    // then
                    assertThat(discountInfos).isEmpty();
                })
        );
    }

    @Test
    @DisplayName("주문금액이 10000원이 되지 않으면 이벤트가 진행되지 않는다.")
    public void getDiscountInfosPrice() {
        // given
        LocalDate date1 = createDate(3);
        DiscountDto requestDto = createRequestDto(date1, List.of("양송이수프-1", "제로콜라-1"));
        // when
        List<DiscountInfo> discountInfos = discountEventManager.getDiscountInfos(requestDto);
        // then
        assertThat(discountInfos).isEmpty();
    }

    @Test
    @DisplayName("총 할인 금액을 얻을 수 있다.")
    public void getTotalDiscountPrice() {
        // given
        LocalDate date = createDate(3);
        DiscountDto requestDto = createRequestDto(date, List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        List<DiscountInfo> discountInfos = discountEventManager.getDiscountInfos(requestDto);
        // when
        int totalDiscountPrice = discountEventManager.getTotalDiscountPrice(discountInfos);
        // then
        int result = getResultPrice(discountInfos);
        assertThat(totalDiscountPrice).isEqualTo(result);
    }

    private DiscountDto createRequestDto(LocalDate localDate, List<String> orderRequest) {
        List<DiscountItemDto> orderItem = createOrderItem(orderRequest);
        int sum = orderItem.stream()
                .mapToInt(item -> item.getMenuInfo().getPrice() * item.getQuantity())
                .sum();
        return new DiscountDto(localDate, orderItem, sum);
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

    private int getResultPrice(List<DiscountInfo> discountInfos) {
        return discountInfos.stream()
                .mapToInt(info -> info.getDiscount())
                .sum();
    }

}