package christmas;

import christmas.controller.OrderController;
import christmas.controller.dto.OrderServiceDto;
import christmas.service.OrderService;
import christmas.view.input.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        OrderController orderController = new OrderController(new InputView(), new OrderService());
        OrderServiceDto orderDto = orderController.createOrder();



    }
}
