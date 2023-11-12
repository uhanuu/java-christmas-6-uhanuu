package christmas.domain.event;

import christmas.domain.event.discount.DiscountEventManager;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.domain.event.item.GiftItem;
import christmas.domain.event.item.GiftItemEventManager;
import christmas.service.dto.DiscountDto;

import java.util.List;
import java.util.Optional;

public class EventManager {
    private final DiscountEventManager discountEventManager;
    private final GiftItemEventManager giftItemEventManager;

    public EventManager(DiscountEventManager discountEventManager, GiftItemEventManager giftItemEventManager) {
        this.discountEventManager = discountEventManager;
        this.giftItemEventManager = giftItemEventManager;
    }

    public List<DiscountInfo> getDiscountInfos(DiscountDto discountDto) {
        return discountEventManager.getDiscountInfos(discountDto);
    }

    public int totalDiscountPrice(List<DiscountInfo> discountInfos) {
        return discountEventManager.totalDiscountPrice(discountInfos);
    }

    public Optional<GiftItem> getGiftItem(int totalOrderPrice) {
        return giftItemEventManager.getGiftItem(totalOrderPrice);
    }

    public String getBadgeName(int totalEventPrice) {
        EventBadge eventBadge = EventBadge.getBadgeByTotalEventPrice(totalEventPrice);
        return eventBadge.getName();
    }

}
