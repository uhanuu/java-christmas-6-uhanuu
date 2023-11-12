package christmas.view.output;

import christmas.controller.dto.OrderMenuDto;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.domain.event.item.GiftItem;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;

public class ConsoleOutputView {
    private static final String MENU_FORMAT = "%s %s개\n";
    private final NumberFormat orderPriceFormat = new DecimalFormat("###,###원");
    private final NumberFormat benefitPriceFormat = new DecimalFormat("-###,###원");


    public void printAllOrderMenu(List<OrderMenuDto> orderMenus) {
        System.out.println("<주문 메뉴>");
        orderMenus.forEach(this::printOrderMenu);
    }
    private void printOrderMenu(OrderMenuDto orderMenu) {
        String name = orderMenu.getName();
        int quantity = orderMenu.getQuantity();
        System.out.printf(MENU_FORMAT, name, quantity);
    }

    public void printTotalOrderPrice(int totalOrderPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.printf(orderPriceFormat.format(totalOrderPrice));
    }

    public void printGiftMenu(Optional<GiftItem> giftItem) {
        System.out.println("<증정 메뉴>");
        giftItem.ifPresentOrElse(
                item -> System.out.printf(MENU_FORMAT, item.getName(), item.getQuantity()),
                () -> System.out.println("없음")
        );
    }

    public void printBenefitDetails(List<DiscountInfo> discountInfos, Optional<GiftItem> giftItem) {
        System.out.println("<혜택 내역>");
        discountInfos
                .forEach(info -> System.out.printf(info.getMessage(), info.getDiscount()));

        giftItem.ifPresent(
                item -> System.out.printf("증정 이벤트: %s", item.getPrice()));
    }

    public void printTotalBenefitPrice(int totalBenefitPrice) {
        System.out.println("<총혜택 금액>");
        System.out.println(benefitPriceFormat.format(totalBenefitPrice));
    }

    public void printEstimatedPayment(int estimatedPayment) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(orderPriceFormat.format(estimatedPayment));
    }

    public void printDecemberEventBadge(String badgeName) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badgeName);
    }

}
