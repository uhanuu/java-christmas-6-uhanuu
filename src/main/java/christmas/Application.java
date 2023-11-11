package christmas;

import christmas.controller.OrderController;
import christmas.controller.dto.OrderServiceDto;
import christmas.domain.event.DiscountEventManager;
import christmas.domain.event.discount.DiscountPolicy;
import christmas.domain.event.discount.christmas.ChristmasDiscountPolicy;
import christmas.domain.event.discount.special.SpecialDiscountPolicy;
import christmas.domain.event.discount.week.DayOfWeekDiscountPolicy;
import christmas.service.OrderService;
import christmas.view.input.InputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        List<DiscountPolicy> discountPolicies =
                List.of(new ChristmasDiscountPolicy(), new SpecialDiscountPolicy(), new DayOfWeekDiscountPolicy());

        OrderController orderController =
                new OrderController(new InputView(), new OrderService(new DiscountEventManager(discountPolicies)));

        OrderServiceDto orderDto = orderController.createOrder();



    }
}
