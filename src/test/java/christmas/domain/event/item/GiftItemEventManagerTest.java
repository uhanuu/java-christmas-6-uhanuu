package christmas.domain.event.item;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GiftItemEventManagerTest {

    private GiftItemEventManager eventManager = new GiftItemEventManager();

    @Test
    @DisplayName("총 주문금액이 12만원 이상이면 샴페인을 증정한다.")
    public void getGiftItem() {
        // given
        int totalOrderPrice = 120_000;
        // when
        Optional<GiftItem> giftItem = eventManager.getGiftItem(totalOrderPrice);
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
        Optional<GiftItem> giftItem = eventManager.getGiftItem(totalOrderPrice);
        // then
        assertTrue(giftItem.isEmpty());
    }

    private GiftItem createGiftItem() {
        return GiftItem.of(PromotionInfo.CHAMPAGNE);
    }

}