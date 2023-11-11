package christmas;

import christmas.controller.OrderController;
import christmas.controller.dto.ChristMaxOrderDto;
import christmas.domain.order.Order;
import christmas.service.ChristMasOrderService;
import christmas.view.input.InputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현

        OrderController orderController = new OrderController(new InputView(), new ChristMasOrderService());
        ChristMaxOrderDto orderDto = orderController.createOrder();



    }
}
