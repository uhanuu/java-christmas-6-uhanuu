package christmas;

import christmas.controller.OrderController;
import christmas.domain.event.EventManager;
import christmas.domain.event.discount.DiscountEventManager;
import christmas.domain.event.item.GiftItemEventManager;
import christmas.domain.event.discount.DiscountPolicy;
import christmas.domain.event.discount.christmas.ChristmasDiscountPolicy;
import christmas.domain.event.discount.special.SpecialDiscountPolicy;
import christmas.domain.event.discount.week.DayOfWeekDiscountPolicy;
import christmas.service.OrderService;
import christmas.view.input.InputView;
import christmas.view.output.ConsoleOutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        List<DiscountPolicy> discountPolicies =
                List.of(new ChristmasDiscountPolicy(), new SpecialDiscountPolicy(), new DayOfWeekDiscountPolicy());

        EventManager eventManager = new EventManager(new DiscountEventManager(discountPolicies), new GiftItemEventManager());

        OrderController orderController =
                new OrderController(new InputView(), new OrderService(eventManager) ,new ConsoleOutputView());

        orderController.run();
    }
}
