package christmas.view.output;

import christmas.controller.dto.OrderMenuDto;
import christmas.domain.event.item.GiftItem;
import christmas.service.dto.DiscountInfoWithGiftItemDTO;
import christmas.view.output.constants.OutputMessage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static christmas.view.output.constants.PriceFormat.BENEFIT_PRICE_FORMAT;
import static christmas.view.output.constants.PriceFormat.ORDER_PRICE_FORMAT;

public class ConsoleOutputView {

    public void printStartMessage(LocalDate localDate) {
        int month = localDate.getMonth().getValue();
        int dayOfMonth = localDate.getDayOfMonth();
        System.out.println(String.format(OutputMessage.START_MESSAGE_FORMAT.getMessage(), month, dayOfMonth));
        newLine();
    }

    public void printAllOrderMenu(List<OrderMenuDto> orderMenus) {
        System.out.println(OutputMessage.ORDER_MENU_MESSAGE.getMessage());
        orderMenus.forEach(this::printOrderMenu);
        newLine();
    }
    private void printOrderMenu(OrderMenuDto orderMenu) {
        String name = orderMenu.getName();
        int quantity = orderMenu.getQuantity();
        System.out.printf(OutputMessage.MENU_FORMAT.getMessage(), name, quantity);
    }

    public void printTotalOrderPrice(int totalOrderPrice) {
        System.out.println(OutputMessage.TOTAL_ORDER_PRICE_MESSAGE.getMessage());
        System.out.println(ORDER_PRICE_FORMAT.format(totalOrderPrice));
        newLine();
    }

    public void printGiftMenu(Optional<GiftItem> giftItem) {
        System.out.println(OutputMessage.GIFT_ITEM_MESSAGE.getMessage());
        giftItem.ifPresentOrElse(
                item -> System.out.printf(OutputMessage.MENU_FORMAT.getMessage(), item.getName(), item.getQuantity()),
                () -> System.out.print(OutputMessage.NO_ITEM_MESSAGE.getMessage()));

        newLine();
    }

    public void printBenefitDetails(List<DiscountInfoWithGiftItemDTO> discountInfosWithGiftItem) {
        System.out.println(OutputMessage.BENEFIT_DETAILS_MESSAGE.getMessage());

        if (discountInfosWithGiftItem.isEmpty()) {
            System.out.print(OutputMessage.NO_ITEM_MESSAGE.getMessage());
        }
        discountInfosWithGiftItem.forEach(info -> System.out.println(
                        String.format(info.getMessage(), BENEFIT_PRICE_FORMAT.format(info.getDiscount()))));
        newLine();
    }

    public void printTotalBenefitPrice(int totalBenefitPrice) {
        System.out.println(OutputMessage.TOTAL_BENEFIT_PRICE_MESSAGE.getMessage());
        System.out.println(getPriceFormat(totalBenefitPrice));
        newLine();
    }

    private String getPriceFormat(int totalBenefitPrice) {
        String formattedPrice = BENEFIT_PRICE_FORMAT.format(totalBenefitPrice);
        if (totalBenefitPrice == 0) {
            formattedPrice = ORDER_PRICE_FORMAT.format(totalBenefitPrice);
        }
        return formattedPrice;
    }

    public void printEstimatedPayment(int estimatedPayment) {
        System.out.println(OutputMessage.ESTIMATED_PAYMENT_MESSAGE.getMessage());
        System.out.println(ORDER_PRICE_FORMAT.format(estimatedPayment));
        newLine();
    }

    public void printDecemberEventBadge(String badgeName) {
        System.out.println(OutputMessage.DECEMBER_EVENT_BADGE_MESSAGE.getMessage());
        System.out.print(badgeName);
    }

    private void newLine() {
        System.out.println();
    }

}
