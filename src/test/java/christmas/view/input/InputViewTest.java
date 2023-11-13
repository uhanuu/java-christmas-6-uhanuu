package christmas.view.input;

import camp.nextstep.edu.missionutils.Console;
import christmas.view.DisplayTestSupport;
import christmas.view.input.validator.InputValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class InputViewTest extends DisplayTestSupport {

    private final InputView inputView = new InputView(new InputValidator());

    @AfterEach
    void tearDown(){
        Console.close();
    }

    @Test
    @DisplayName("사용자에게 예상 요일을 입력받을 수 있다.")
    public void requestVisitDate() {
        // given
        String input = "26";
        LocalDate result = LocalDate.of(2023, 12, 26);
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        // when
        LocalDate localDate = inputView.requestVisitDate();
        // then
        assertThat(localDate).isEqualTo(result);
    }

    @Test
    @DisplayName("사용자에게 예상 요일을 요청하는 메시지를 console에 출력할 수 있다.")
    public void printRequestVisitDate() {
        // given
        String input = "26";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String result = """
                안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.
                12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
                """;
        // when
        inputView.requestVisitDate();
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

    @Test
    @DisplayName("사용자에게 주문할 상품들을 포맷에 맞쳐서 요청받을 수 있다.")
    public void requestOrderItems() {
        // given
        String input = "해산물파스타-2,레드와인-1,초코케이크-1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        // when
        List<String> requestOrderItems = inputView.requestOrderItems();
        // then
        assertThat(requestOrderItems).hasSize(3)
                .contains("해산물파스타-2", "레드와인-1", "초코케이크-1");
    }

    @Test
    @DisplayName("사용자에게 주문할 상품을 요청하는 메시지를 console에 출력할 수 있다.")
    public void printRequestOrderItems() {
        // given
        String input = "해산물파스타-2,레드와인-1,초코케이크-1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String result = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)\n";
        // when
        inputView.requestOrderItems();
        // then
        assertThat(result).isEqualTo(outputNotTrim());
    }

}