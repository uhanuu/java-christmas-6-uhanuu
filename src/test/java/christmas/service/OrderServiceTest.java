package christmas.service;

import christmas.domain.event.EventManager;
import christmas.domain.event.discount.DiscountEventManager;
import christmas.domain.event.discount.christmas.ChristmasDiscountPolicy;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.domain.event.discount.special.SpecialDiscountPolicy;
import christmas.domain.event.discount.week.DayOfWeekDiscountPolicy;
import christmas.domain.event.item.GiftItem;
import christmas.domain.event.item.GiftItemEventManager;
import christmas.domain.event.item.PromotionInfo;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.domain.order.OrderItems;
import christmas.service.dto.DiscountDto;
import christmas.service.dto.DiscountInfoWithGiftItemDTO;
import christmas.service.dto.DiscountItemDto;
import christmas.util.ConvertOrderItem;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderServiceTest {

    private EventManager eventManager = new EventManager(new DiscountEventManager(
            List.of(new SpecialDiscountPolicy(), new DayOfWeekDiscountPolicy(), new ChristmasDiscountPolicy())),
            new GiftItemEventManager());
    private OrderService orderService = new OrderService(eventManager);

    @Test
    @DisplayName("주문 내역을 통해서 이벤트 이름과 할인된 금액을 얻을 수 있다.")
    public void getDiscountInfos() {
        // given
        LocalDate date = createDate(26);
        Order order = createOrder(date, List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        // when
        List<DiscountInfo> discountInfos = orderService.getDiscountInfos(order);
        discountInfos.forEach(System.out::println);
        // then
        assertThat(discountInfos).hasSize(1)
                .extracting("message", "discount")
                .contains(
                        Tuple.tuple("평일 할인: %s", 4046));
    }

    @Test
    @DisplayName("주문금액이 10000원이 되지 않으면 이벤트가 진행되지 않는다.")
    public void getDiscountInfosPrice() {
        // given
        LocalDate date1 = createDate(3);
        Order order = createOrder(date1, List.of("양송이수프-1", "제로콜라-1"));
        // when
        List<DiscountInfo> discountInfos = orderService.getDiscountInfos(order);
        // then
        assertThat(discountInfos).isEmpty();
    }

    @Test
    @DisplayName("총 할인 금액을 얻을 수 있다.")
    public void getTotalDiscountPrice() {
        // given
        LocalDate date = createDate(3);
        DiscountDto requestDto = createRequestDto(date, List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        List<DiscountInfo> discountInfos = eventManager.getDiscountInfos(requestDto);
        // when
        int totalDiscountPrice = orderService.getTotalDiscountPrice(discountInfos);
        // then
        int result = getResultPrice(discountInfos);
        assertThat(totalDiscountPrice).isEqualTo(result);
    }

    @Test
    @DisplayName("총 주문금액이 12만원 이상이면 샴페인을 증정한다.")
    public void getGiftItem() {
        // given
        int totalOrderPrice = 120_000;
        // when
        Optional<GiftItem> giftItem = orderService.getGiftItem(totalOrderPrice);
        // then
        assertTrue(giftItem.isPresent());
        assertThat(giftItem).hasValue(createGiftItem());
    }

    @Test
    @DisplayName("총 주문금액이 12만원 미만이면 증정 상품이 없다.")
    public void getGiftItemEmpty() {
        // given
        int totalOrderPrice = 119_999;
        // when
        Optional<GiftItem> giftItem = orderService.getGiftItem(totalOrderPrice);
        // then
        assertTrue(giftItem.isEmpty());
    }

    @Test
    @DisplayName("할인 금액이 5000미만이면 없음을 반환한다.")
    public void noneTest() {
        // given
        int totalEventPrice = 4999;
        // when
        String name = orderService.getBadgeName(totalEventPrice);
        // then
        assertThat(name).isEqualTo("없음");
    }

    @Test
    @DisplayName("할인 금액이 5000원 이상 10000미만이면 별을 반환한다.")
    public void starTest() {
        // given
        int totalEventPrice = 9999;
        // when
        String name = orderService.getBadgeName(totalEventPrice);
        // then
        assertThat(name).isEqualTo("별");
    }

    @Test
    @DisplayName("할인 금액이 5000원 이상 20000미만이면 트리를 반환한다.")
    public void treeTest() {
        // given
        int totalEventPrice = 19999;
        // when
        String name = orderService.getBadgeName(totalEventPrice);
        // then
        assertThat(name).isEqualTo("트리");
    }

    @Test
    @DisplayName("할인 금액이 20000이상이면 산타를 반환한다.")
    public void santaTest() {
        // given
        int totalEventPrice = 20000;
        // when
        String name = orderService.getBadgeName(totalEventPrice);
        // then
        assertThat(name).isEqualTo("산타");
    }

    @Test
    @DisplayName("증정 이벤트를 더해서 dto를 만들 수 있다.")
    public void addGiftItemToDiscountInfos() {
        // given
        LocalDate date = createDate(26);
        Order order = createOrder(date, List.of("티본스테이크-1", "바비큐립-1", "초코케이크-2", "제로콜라-1"));
        List<DiscountInfo> discountInfos = orderService.getDiscountInfos(order);

        int totalOrderPrice = 120_000;
        Optional<GiftItem> giftItem = orderService.getGiftItem(totalOrderPrice);
        // when
        List<DiscountInfoWithGiftItemDTO> withGiftItems =
                orderService.addGiftItemToDiscountInfos(discountInfos, giftItem);
        // then
        assertThat(withGiftItems).hasSize(2)
                .extracting("message", "discount")
                .contains(
                        Tuple.tuple("증정 이벤트: %s", 25000));
    }

    private LocalDate createDate(int dayOfMonth) {
        return LocalDate.of(2023, 12, dayOfMonth);
    }

    private Order createOrder(LocalDate localDate, List<String> orderItemNames) {
        List<OrderItem> allMenu = ConvertOrderItem.getAllMenu(orderItemNames);
        OrderItems orderItems = new OrderItems(allMenu);
        return new Order(localDate, orderItems);
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

    private int getResultPrice(List<DiscountInfo> discountInfos) {
        return discountInfos.stream()
                .mapToInt(info -> info.getDiscount())
                .sum();
    }

    private GiftItem createGiftItem() {
        return GiftItem.of(PromotionInfo.CHAMPAGNE);
    }

}