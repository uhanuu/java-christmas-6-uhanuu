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

    public List<DiscountInfo> getDiscountInfos(final DiscountDto discountDto) {
        //List<DiscountInfo>를 가지는 컬렉션 만들어서 view에 넘길 데이터 가공하기, Manage에서 총 이벤트들의 내용을 전달 받는식으로 받아서 컬렉션에서 처리
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
