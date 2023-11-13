package christmas.domain.event.item;

import java.util.Optional;

public class GiftItemEventManager {

    public Optional<GiftItem> getGiftItem(int totalOrderPrice) {
        return Optional.of(PromotionInfo.getGiftItem(totalOrderPrice))
                .filter(PromotionInfo::isPresent)
                .map(GiftItem::of);
    }

}
