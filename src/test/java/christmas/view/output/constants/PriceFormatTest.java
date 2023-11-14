package christmas.view.output.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PriceFormatTest {

    @Test
    @DisplayName("주문 금액은 양수 포맷의 숫자로 반환받을 수 있다.")
    public void positivePriceFormatTest() {
        // given
        int price = 1000;
        String result = "1,000원";
        // when
        String format = PriceFormat.ORDER_PRICE_FORMAT.format(1000);
        // then
        assertEquals(result, format);
    }

    @Test
    @DisplayName("혜택 받은 금액은 음수 포맷의 숫자로 반환받을 수 있다.")
    public void negativePriceFormatTest() {
        // given
        int price = 1000;
        String result = "-1,000원";
        // when
        String format = PriceFormat.BENEFIT_PRICE_FORMAT.format(1000);
        // then
        assertEquals(result, format);
        assertTrue(format.contains("-"));
    }
}