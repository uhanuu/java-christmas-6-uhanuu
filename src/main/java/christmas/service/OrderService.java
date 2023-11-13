package christmas.service;

import christmas.domain.event.EventManager;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.domain.event.item.GiftItem;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItems;
import christmas.service.dto.DiscountDto;
import christmas.service.dto.DiscountInfoWithGiftItemDTO;
import christmas.service.dto.DiscountItemDto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class OrderService {

    private final EventManager eventManager;

    public OrderService(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    public List<DiscountInfo> getDiscountInfos(Order order) {
        LocalDate localDate = order.getLocalDate();
        OrderItems orderItems = order.getOrderItems();

        DiscountDto discountDto = createDiscountDto(localDate, orderItems);
        return eventManager.getDiscountInfos(discountDto);
    }

    private DiscountDto createDiscountDto(LocalDate localDate, OrderItems orderItems) {
        List<DiscountItemDto> discountItemsDto = orderItems.getOrderItems().stream()
                .map(DiscountItemDto::of)
                .toList();

        return new DiscountDto(localDate, discountItemsDto, orderItems.getTotalPrice());
    }

    public int getTotalDiscountPrice(List<DiscountInfo> discountInfos) {

        return eventManager.totalDiscountPrice(discountInfos);
    }

    public Optional<GiftItem> getGiftItem(int totalOrderPrice) {
        return eventManager.getGiftItem(totalOrderPrice);
    }

    public String getBadgeName(int totalEventPrice) {
        return eventManager.getBadgeName(totalEventPrice);
    }

    public List<DiscountInfoWithGiftItemDTO> addGiftItemToDiscountInfos(List<DiscountInfo> discountInfos,
                                                                        Optional<GiftItem> giftItem) {

        List<DiscountInfoWithGiftItemDTO> discountInfosWithGiftItem = discountInfos.stream()
                .map(info -> new DiscountInfoWithGiftItemDTO(info.getMessage(), info.getDiscount()))
                .collect(Collectors.toList());

        giftItem.ifPresent(item ->
                discountInfosWithGiftItem.add(new DiscountInfoWithGiftItemDTO(item.getMessage(), item.getPrice())));

        return Collections.unmodifiableList(discountInfosWithGiftItem);
    }
}
