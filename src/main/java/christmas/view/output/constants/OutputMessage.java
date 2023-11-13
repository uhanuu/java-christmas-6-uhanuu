package christmas.view.output.constants;

public enum OutputMessage {
    MENU_FORMAT("%s %s개\n"),
    START_MESSAGE_FORMAT("%s월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_MESSAGE("<주문 메뉴>"),
    TOTAL_ORDER_PRICE_MESSAGE("<할인 전 총주문 금액>"),
    GIFT_ITEM_MESSAGE("<증정 메뉴>"),
    NO_ITEM_MESSAGE("없음\n"),
    BENEFIT_DETAILS_MESSAGE("<혜택 내역>"),
    TOTAL_BENEFIT_PRICE_MESSAGE("<총혜택 금액>"),
    ESTIMATED_PAYMENT_MESSAGE("<할인 후 예상 결제 금액>"),
    DECEMBER_EVENT_BADGE_MESSAGE("<12월 이벤트 배지>");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
