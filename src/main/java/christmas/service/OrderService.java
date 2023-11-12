package christmas.service;

import christmas.controller.dto.OrderServiceDto;
import christmas.domain.event.DiscountEventManager;
import christmas.domain.order.OrderItems;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;


public class OrderService {

    private final DiscountEventManager discountEventManager;

    public OrderService(DiscountEventManager discountEventManager) {
        this.discountEventManager = discountEventManager;
    }
//
//    public int getTotalDiscountPrice(OrderServiceDto orderServiceDto) {
//        LocalDate localDate = orderServiceDto.getLocalDate();
//        OrderItems orderItems = orderServiceDto.getOrderItems();
//
//        DiscountDto discountDto = new DiscountDto(localDate, orderItems.getOrderItems());
//        return discountEventManager.calculateTotalDiscountPrice(discountDto);
//    }

    private boolean canProcessEvent(LocalDate localDate, int totalPrice) {
        if (!EventRule.isCurrentDate(localDate)) {
            return false;
        }
        return isTotalPriceAboveMinimum(totalPrice);
    }

    private boolean isTotalPriceAboveMinimum(int totalPrice) {
        return EventRule.isTotalPriceAboveMinimum(totalPrice);
    }

}
