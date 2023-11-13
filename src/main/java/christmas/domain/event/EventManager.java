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
        List<DiscountInfo> discountInfos = discountEventManager.getDiscountInfos(discountDto);

        return discountInfos.stream()
                .filter(info -> info.getDiscount() != 0)
                .toList();
    }

    public int getTotalDiscountPrice(List<DiscountInfo> discountInfos) {
        return discountEventManager.getTotalDiscountPrice(discountInfos);
    }

    public Optional<GiftItem> getGiftItem(int totalOrderPrice) {
        return giftItemEventManager.getGiftItem(totalOrderPrice);
    }

    public String getBadgeName(int totalEventPrice) {
        EventBadge eventBadge = EventBadge.getBadgeByTotalEventPrice(totalEventPrice);
        return eventBadge.getName();
    }

}
