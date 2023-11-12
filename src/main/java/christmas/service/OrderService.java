package christmas.service;

import christmas.controller.dto.OrderServiceDto;
import christmas.domain.event.DiscountEventManager;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.domain.order.OrderItems;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;
import java.util.List;


public class OrderService {

    private final DiscountEventManager discountEventManager;

    public OrderService(DiscountEventManager discountEventManager) {
        this.discountEventManager = discountEventManager;
    }

    public List<DiscountInfo> getDiscountInfos(OrderServiceDto orderServiceDto) {
        LocalDate localDate = orderServiceDto.getLocalDate();
        OrderItems orderItems = orderServiceDto.getOrderItems();

        DiscountDto discountDto = createDiscountDto(localDate, orderItems);
        return discountEventManager.getDiscountInfos(discountDto);
    }

    private static DiscountDto createDiscountDto(LocalDate localDate, OrderItems orderItems) {
        DiscountDto discountDto = new DiscountDto(localDate, orderItems.getOrderItems(), orderItems.getTotalPrice());
        return discountDto;
    }

}
