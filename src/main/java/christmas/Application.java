package christmas;

import christmas.controller.OrderController;
import christmas.domain.order.Order;
import christmas.view.input.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        OrderController orderController = new OrderController(new InputView());
        Order order = orderController.createOrder();
        order.getOrderItems().stream()
                .forEach(System.out::println);

    }
}
