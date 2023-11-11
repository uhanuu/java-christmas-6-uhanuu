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

    public boolean christMaxDiscountPrice(OrderServiceDto orderServiceDto) {
        LocalDate localDate = orderServiceDto.getLocalDate();
        OrderItems orderItems = orderServiceDto.getOrderItems();

        if (!EventRule.isCurrentDate(localDate)) {
            return false;
        }
        return EventRule.isTotalPriceAboveMinimum(orderItems.getTotalPrice());
    }

    public int getDisCountAmount(OrderServiceDto orderServiceDto) {
        LocalDate localDate = orderServiceDto.getLocalDate();
        OrderItems orderItems = orderServiceDto.getOrderItems();

        DiscountDto discountDto = new DiscountDto(localDate, orderItems.getOrderItems());
        return 0;
    }

}
