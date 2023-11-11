package christmas.domain.event;

import christmas.domain.event.discount.DiscountPolicy;
import christmas.service.dto.DiscountDto;

import java.util.List;

public class DiscountEventManager {

    private final List<DiscountPolicy> discountPolicies;

    public DiscountEventManager(List<DiscountPolicy> discountPolicies) {
        this.discountPolicies = discountPolicies;
    }

    public int calculateTotalDiscountPrice(final DiscountDto discountDto) {
        return discountPolicies.stream()
                .mapToInt(policy -> policy.discount(discountDto))
                .sum();
    }

}
