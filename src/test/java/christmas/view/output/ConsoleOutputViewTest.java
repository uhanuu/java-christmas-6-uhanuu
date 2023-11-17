package christmas.view.output;

import christmas.controller.dto.OrderMenuDto;
import christmas.domain.event.item.GiftItem;
import christmas.domain.event.item.PromotionInfo;
import christmas.domain.order.OrderItem;
import christmas.menu.MenuInfo;
import christmas.service.dto.DiscountInfoWithGiftItemDTO;
import christmas.view.DisplayTestSupport;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ConsoleOutputViewTest extends DisplayTestSupport {

    private ConsoleOutputView consoleOutputView = new ConsoleOutputView();

    @Test
    @DisplayName("시작 메시지를 출력할 수 있다.")
    public void printStartMessage() {
        // given
        String result = """
                12월 1일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!
                                
                """;
        LocalDate date = LocalDate.of(2023, 12, 1);
        // when
        consoleOutputView.printStartMessage(date);
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    @Test
    @DisplayName("주문한 모든 메뉴와 수량을 출력할 수 있다.")
    public void printAllOrderMenu() {
        // given
        List<OrderMenuDto> orderMenus = createOrderMenus(createOrderMenu(MenuInfo.BBQ_RIBS, 2),
                createOrderMenu(MenuInfo.CHOCOLATE_CAKE, 1),
                createOrderMenu(MenuInfo.ZERO_COLA, 1));

        String result = """
                <주문 메뉴>
                바비큐립 2개
                초코케이크 1개
                제로콜라 1개
                                
                """;
        // when
        consoleOutputView.printAllOrderMenu(orderMenus);
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    @Test
    @DisplayName("총 주문금액을 출력할 수 있다.")
    public void printTotalOrderPrice() {
        // given
        int totalOrderPrice = 15000;
        String result = """
                <할인 전 총주문 금액>
                15,000원
                                
                """;
        // when
        consoleOutputView.printTotalOrderPrice(totalOrderPrice);
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    @Test
    @DisplayName("증정 상품이 있다면 출력할 수 있다.")
    public void printGiftMenu() {
        // given
        Optional<GiftItem> giftItem = createGiftItem();
        String result = """
                <증정 메뉴>
                샴페인 1개
                                
                """;
        // when
        consoleOutputView.printGiftMenu(giftItem);
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    @Test
    @DisplayName("증정 상품이 없다면 없음을 출력할 수 있다.")
    public void printNotGiftMenu() {
        // given
        Optional<GiftItem> giftItem = Optional.empty();
        String result = """
                <증정 메뉴>
                없음
                                
                """;
        // when
        consoleOutputView.printGiftMenu(giftItem);
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    @Test
    @DisplayName("증정 상품가격을 포함한 혜택받은 이벤트들의 종류와 금액을 각각 출력할 수 있다.")
    public void printBenefitDetails() {
        // given
        List<DiscountInfoWithGiftItemDTO> discountWithGiftItem = createDiscountWithGiftItem();
        String result = """
                <혜택 내역>
                크리스마스 디데이 할인: -1,200원
                평일 할인: -4,046원
                특별 할인: -1,000원
                증정 이벤트:: -25,000원
                
                """;
        // when
        consoleOutputView.printBenefitDetails(discountWithGiftItem);
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    @Test
    @DisplayName("총 증정 상품가격을 포함한 혜택받은 금액이 없을 경우 없음을 출력한다.")
    public void printNoBenefitDetails() {
        // given
        List<DiscountInfoWithGiftItemDTO> discountWithGiftItem = Collections.emptyList();
        String result = """
                <혜택 내역>
                없음
                
                """;
        // when
        consoleOutputView.printBenefitDetails(discountWithGiftItem);
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    @Test
    @DisplayName("증정 상품을 포함한 총 혜택받은 금액을 음수 포맷으로 출력받을 수 있다.")
    public void printTotalBenefitPrice() {
        // given
        int totalBenefitPrice = 34000;
        String result = """
                <총혜택 금액>
                -34,000원
                
                """;
        // when
        consoleOutputView.printTotalBenefitPrice(totalBenefitPrice);
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    @Test
    @DisplayName("혜택받은 금액이 없다면 0원을 출력한다.")
    public void printNotTotalBenefitPrice() {
        // given
        int totalBenefitPrice = 0;
        String result = """
                <총혜택 금액>
                0원
                
                """;
        // when
        consoleOutputView.printTotalBenefitPrice(totalBenefitPrice);
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    @Test
    @DisplayName("총 주문 금액에서 증정 상품 가격을 제외한 총혜택 금액을 뺀 가격을 출력한다.")
    public void printEstimatedPayment() {
        // given
        int estimatedPayment = 32000;
        String result = """
                <할인 후 예상 결제 금액>
                32,000원
                
                """;
        // when
        consoleOutputView.printEstimatedPayment(estimatedPayment);
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    @Test
    @DisplayName("이벤트 배지를 출력할 수 있다.")
    public void printDecemberEventBadge() {
        // given
        String badgeName = "산타";
        String result = """
                <12월 이벤트 배지>
                산타""";
        // when
        consoleOutputView.printDecemberEventBadge(badgeName);
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    private OrderMenuDto createOrderMenu(MenuInfo menuInfo, int quantity) {
        return OrderMenuDto.of(new OrderItem(menuInfo, quantity));
    }

    private List<OrderMenuDto> createOrderMenus(OrderMenuDto... menus) {
        return List.of(menus);
    }

    private Optional<GiftItem> createGiftItem() {
        return Optional.of(GiftItem.of(PromotionInfo.CHAMPAGNE));
    }

    private List<DiscountInfoWithGiftItemDTO> createDiscountWithGiftItem() {
        DiscountInfoWithGiftItemDTO info1 = new DiscountInfoWithGiftItemDTO("크리스마스 디데이 할인: %s", 1200);
        DiscountInfoWithGiftItemDTO info2 = new DiscountInfoWithGiftItemDTO("평일 할인: %s", 4046);
        DiscountInfoWithGiftItemDTO info3 = new DiscountInfoWithGiftItemDTO("특별 할인: %s", 1000);
        DiscountInfoWithGiftItemDTO info4 = new DiscountInfoWithGiftItemDTO("증정 이벤트:: %s", 25000);
        return List.of(info1, info2, info3, info4);
    }
}