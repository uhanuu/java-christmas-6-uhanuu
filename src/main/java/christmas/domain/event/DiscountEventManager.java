package christmas.domain.event;

import christmas.domain.event.discount.DiscountPolicy;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.service.dto.DiscountDto;

import java.util.List;

public class DiscountEventManager {

    private final List<DiscountPolicy> discountPolicies;

    public DiscountEventManager(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public List<DiscountInfo> getDiscountInfos(final DiscountDto discountDto) {
        return discountPolicies.stream()
                .map(policy -> policy.discount(discountDto))
                .toList();
    }

}
