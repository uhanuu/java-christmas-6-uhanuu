package christmas.service;

import christmas.controller.dto.DiscountOrderServiceDto;
import christmas.domain.event.EventManager;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.domain.order.OrderItems;
import christmas.service.dto.DiscountDto;

import java.time.LocalDate;
import java.util.List;


public class OrderService {

    private final EventManager eventManager;

    public OrderService(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public List<DiscountInfo> getDiscountInfos(DiscountOrderServiceDto discountOrderServiceDto) {
        LocalDate localDate = discountOrderServiceDto.getLocalDate();
        OrderItems orderItems = discountOrderServiceDto.getOrderItems();

        DiscountDto discountDto = createDiscountDto(localDate, orderItems);
        return eventManager.getDiscountInfos(discountDto);
    }

    private DiscountDto createDiscountDto(LocalDate localDate, OrderItems orderItems) {
        return new DiscountDto(localDate, orderItems.getOrderItems(), orderItems.getTotalPrice());
    }

    public int getTotalDiscountPrice(List<DiscountInfo> discountInfos) {
        return eventManager.totalDiscountPrice(discountInfos);
    }
}
