package christmas;

import christmas.config.OrderControllerConfig;
import christmas.controller.OrderController;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        OrderControllerConfig orderControllerConfig = new OrderControllerConfig();

        OrderController orderController = orderControllerConfig.getOrderController();
        orderController.run();
    }
}
