package christmas.domain.view.input.validator;

import christmas.view.input.validator.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class InputValidatorTest {

    private final InputValidator validator = new InputValidator();

    @ParameterizedTest
    @DisplayName("사용자에게 숫자를 입력받을 수 있다.")
    @ValueSource(strings = {"1", "2", "48", "128"})
    public void parseIntTest(String input) {
        // given
        int result = Integer.parseInt(input);
        // when
        int monthOfDate = validator.parseInt(input);
        // then
        assertThat(result).isEqualTo(monthOfDate);
    }

    @ParameterizedTest(name = "{0}가 들어오면 예외가 발생한다.")
    @DisplayName("사용자 입력이 숫자가 아니면 예외가 발생한다.")
    @ValueSource(strings = {"asdf", "하하호호", "123asd", "하하123"})
    public void parseIntExceptionTest(String input) {
        // when // then
        assertThatThrownBy(() -> validator.parseInt(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "날짜로 {0}은 정상적인 날짜이다.")
    @DisplayName("사용자에게 날짜를 받아서 LocalDate를 반환할 수 있다.")
    @ValueSource(ints = {1, 2, 30, 31})
    public void validateDateTest(int monthOfDate) {
        // given
        LocalDate result = LocalDate.of(2023, 12, monthOfDate);
        // when
        LocalDate request = validator.validateDate(monthOfDate);
        // then
        assertThat(result).isEqualTo(request);
    }

    @ParameterizedTest(name = "2023년 12월 {0}일은 존재하지 않는다.")
    @DisplayName("2023년 12월에 해당하는 일이 아니면 예외가 발생한다.")
    @ValueSource(ints = {-1, 0, 32, 33})
    public void validateDateExceptionTest(int monthOfDate) {
        // when // then
        assertThatThrownBy(() -> validator.validateDate(monthOfDate))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "{0}은 일치하는 입력 폼입니다.")
    @DisplayName("사용자 주문의 입력 폼을 검증한다.")
    @ValueSource(strings = {"티본스테이크-1", "바비큐립-10", "초코케이크-2", "제로콜라-1"})
    public void requestOrderItems(String input) {
        // given
        List<String> requestItems = requestItems(input);
        // when
        validator.validateOrderItemsForm(requestItems);
        // then
    }

    @ParameterizedTest(name = "{0}은 입력 폼과 다릅니다.")
    @DisplayName("사용자 주문의 입력 폼이 잘못되면 예외가 발생한다.")
    @ValueSource(strings = {"티본스테이크1-1", "바비큐립", "asd-2", "제로콜라-100", "제로콜라-0", "티본스테이크-", "-3", " ", ""})
    public void requestOrderItemsException(String input) {
        // given
        List<String> requestItems = requestItems(input);
        requestItems.forEach(System.out::println);
        // when //then
        assertThatThrownBy(() -> validator.validateOrderItemsForm(requestItems))
                .isInstanceOf(IllegalArgumentException.class);
    }

    public List<String> requestItems(String input) {
        return List.of(input.split(","));
    }

}