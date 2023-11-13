package christmas.domain.event.discount;

import christmas.domain.event.EventRule;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class DiscountEventManager {

    private final List<DiscountPolicy> discountPolicies;

    public DiscountEventManager(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public List<DiscountInfo> getDiscountInfos(DiscountDto discountDto) {
        if (canProcessEvent(discountDto.getLocalDate(), discountDto.getTotalPrice())) {
            return Collections.emptyList();
        }

        return discountPolicies.stream()
                .map(policy -> policy.getDiscountInfo(discountDto))
                .toList();
    }

    private boolean canProcessEvent(LocalDate localDate, int totalPrice) {
        if (!EventRule.isCurrentDate(localDate)) {
            return false;
        }
        return EventRule.isTotalPriceAboveMinimum(totalPrice);
    }

    public int totalDiscountPrice(List<DiscountInfo> discountInfos) {
        return discountInfos.stream()
                .mapToInt(DiscountInfo::getDiscount)
                .sum();
    }

}
