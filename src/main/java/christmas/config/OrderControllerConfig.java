package christmas.config;

import christmas.controller.OrderController;
import christmas.domain.event.EventManager;
import christmas.domain.event.discount.DiscountEventManager;
import christmas.domain.event.discount.DiscountPolicy;
import christmas.domain.event.discount.christmas.ChristmasDiscountPolicy;
import christmas.domain.event.discount.special.SpecialDiscountPolicy;
import christmas.domain.event.discount.week.DayOfWeekDiscountPolicy;
import christmas.domain.event.item.GiftItemEventManager;
import christmas.service.OrderService;
import christmas.view.input.ConsoleInput;
import christmas.view.input.InputView;
import christmas.view.input.validator.InputValidator;
import christmas.view.output.ConsoleOutputView;

import java.util.List;

public class OrderControllerConfig {

    public OrderController getOrderController() {
        return new OrderController(getInputView(), getOrderService(), getOutputView());
    }

    private List<DiscountPolicy> getDiscountPolicies() {
        return List.of(new ChristmasDiscountPolicy(), new SpecialDiscountPolicy(), new DayOfWeekDiscountPolicy());
    }

    private EventManager getEventManager() {
        return new EventManager(new DiscountEventManager(getDiscountPolicies()), new GiftItemEventManager());
    }

    private OrderService getOrderService() {
        return new OrderService(getEventManager());
    }

    private ConsoleInput getInputView() {
        return new InputView(new InputValidator());
    }

    private ConsoleOutputView getOutputView() {
        return new ConsoleOutputView();
    }


}
