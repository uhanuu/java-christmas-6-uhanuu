package christmas.view.output;

import christmas.controller.dto.OrderMenuDto;
import christmas.domain.event.discount.dto.DiscountInfo;
import christmas.domain.event.item.GiftItem;
import christmas.service.dto.DiscountInfoWithGiftItemDTO;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ConsoleOutputView {
    private static final String MENU_FORMAT = "%s %s개\n";
    private static final NumberFormat ORDER_PRICE_FORMAT = new DecimalFormat("###,###원");
    private static final NumberFormat BENEFIT_PRICE_FORMAT = new DecimalFormat("-###,###원");

    public void printStartMessage(LocalDate localDate) {
        int month = localDate.getMonth().getValue();
        int dayOfMonth = localDate.getDayOfMonth();
        String messageFormat = "%s월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";

        System.out.println(String.format(messageFormat, month, dayOfMonth));
        System.out.println();
    }

    public void printAllOrderMenu(List<OrderMenuDto> orderMenus) {
        System.out.println("<주문 메뉴>");
        orderMenus.forEach(this::printOrderMenu);
        System.out.println();
    }
    private void printOrderMenu(OrderMenuDto orderMenu) {
        String name = orderMenu.getName();
        int quantity = orderMenu.getQuantity();
        System.out.printf(MENU_FORMAT, name, quantity);
    }

    public void printTotalOrderPrice(int totalOrderPrice) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(ORDER_PRICE_FORMAT.format(totalOrderPrice));
        System.out.println();
    }

    public void printGiftMenu(Optional<GiftItem> giftItem) {
        System.out.println("<증정 메뉴>");
        giftItem.ifPresentOrElse(
                item -> System.out.printf(MENU_FORMAT, item.getName(), item.getQuantity()),
                () -> System.out.print("없음")
        );
        System.out.println("\n");
    }

    public void printBenefitDetails(List<DiscountInfoWithGiftItemDTO> discountInfosWithGiftItem) {
        System.out.println("<혜택 내역>");

        if (discountInfosWithGiftItem.isEmpty()) {
            System.out.println("없음");
        }
        discountInfosWithGiftItem
                .forEach(info -> System.out.println(
                        String.format(info.getMessage(), BENEFIT_PRICE_FORMAT.format(info.getDiscount()))));

        System.out.println();
    }

    public void printTotalBenefitPrice(int totalBenefitPrice) {
        System.out.println("<총혜택 금액>");
        System.out.println(BENEFIT_PRICE_FORMAT.format(totalBenefitPrice));
        System.out.println();
    }

    public void printEstimatedPayment(int estimatedPayment) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(ORDER_PRICE_FORMAT.format(estimatedPayment));
        System.out.println();
    }

    public void printDecemberEventBadge(String badgeName) {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(badgeName);
    }

}
