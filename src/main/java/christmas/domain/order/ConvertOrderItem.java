package christmas.domain.order;

import christmas.domain.menu.MenuInfo;

import java.util.List;

public class ConvertOrderItem {

    private static final int NAME_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    private ConvertOrderItem() {
    }

    public static List<OrderItem> getAllMenu(List<String> orderItemsForm) {
        return orderItemsForm.stream()
                .map(items -> List.of(items.split("-")))
                .map(ConvertOrderItem::createOrderItem)
                .toList();
    }

    private static OrderItem createOrderItem(List<String> itemForm) {
        String name = itemForm.get(NAME_INDEX);
        int quantity = Integer.parseInt(itemForm.get(QUANTITY_INDEX));

        return new OrderItem(MenuInfo.getInfo(name), quantity);
    }

}
