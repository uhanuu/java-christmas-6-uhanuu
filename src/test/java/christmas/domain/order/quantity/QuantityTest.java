package christmas.domain.order.quantity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;


class QuantityTest {

    @ParameterizedTest
    @DisplayName("주문 수량은 1에서 20개 이하여야 한다.")
    @ValueSource(ints = {1, 2, 18, 19})
    public void quantityTest(int input) {
        // when
        Quantity quantity = new Quantity(input);
        // then
        assertThat(quantity.getQuantity()).isEqualTo(input);
    }

    @ParameterizedTest
    @DisplayName("주문 수량은 1개 미만이거나 20개 초과면 예외가 발생한다.")
    @ValueSource(ints = {-1, 0, 21, 22})
    public void quantityExceptionTest(int input) {
        // when //then
        Assertions.assertThatThrownBy(() -> new Quantity(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}