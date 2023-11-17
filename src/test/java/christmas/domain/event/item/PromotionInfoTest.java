package christmas.domain.event.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PromotionInfoTest {

    @Test
    @DisplayName("총 주문금액이 12만원 이상이면 샴페인을 증정한다.")
    public void getGiftItem() {
        // given
        int totalOrderPrice = 120_000;
        // when
        PromotionInfo giftItem = PromotionInfo.getGiftItem(totalOrderPrice);
        // then
        assertThat(giftItem).isEqualTo(PromotionInfo.CHAMPAGNE);
    }

    @Test
    @DisplayName("총 주문금액이 12만원 미만이면 증정 상품이 없다.")
    public void getGiftItemEmpty() {
        // given
        int totalOrderPrice = 119_999;
        // when
        PromotionInfo giftItem = PromotionInfo.getGiftItem(totalOrderPrice);
        // then
        assertThat(giftItem).isEqualTo(PromotionInfo.EMPTY);
    }

    @Test
    @DisplayName("프로모션의 이벤트 포멧을 반환한다.")
    public void getPromotionEventNameFormat() {
        // given
        String result = "증정 이벤트: %s";
        // when
        String promotionEventNameFormat = PromotionInfo.CHAMPAGNE.getPromotionEventNameFormat();
        // then
        assertThat(promotionEventNameFormat).isEqualTo(result);
    }

    @Test
    @DisplayName("Promotion 값이 Empty가 아니면 true를 반환한다.")
    public void isPresent() {
        // given
        PromotionInfo champagne = PromotionInfo.CHAMPAGNE;
        // when
        boolean isPresent = champagne.isPresent();
        // then
        assertThat(isPresent).isTrue();
    }

    @Test
    @DisplayName("Promotion 값이 Empty면 false를 반환한다.")
    public void isPresentFalse() {
        // given
        PromotionInfo empty = PromotionInfo.EMPTY;
        // when
        boolean isPresent = empty.isPresent();
        // then
        assertThat(isPresent).isFalse();
    }

    @Test
    @DisplayName("getMethod를 통해서 Promotion의 정보를 얻을 수 있다.")
    public void getMethod() {
        // given
        PromotionInfo champagne = PromotionInfo.CHAMPAGNE;
        // when
        String name = champagne.getName();
        int price = champagne.getPrice();
        int quantity = champagne.getQuantity();
        // then
        assertThat(name).isEqualTo("샴페인");
        assertThat(price).isEqualTo(25_000);
        assertThat(quantity).isEqualTo(1);
    }

}