package pl.springcourse.internet_shop.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.springcourse.internet_shop.model.Basket;

import java.math.BigDecimal;

@Service
@Profile("start")
public class BasketStartService {
    private Basket basket;

    BasketStartService()
    {
        basket = new Basket(5);
    }

    public BigDecimal getBill() {
        return basket.getBill();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void viewBill() {
        System.out.println("\nBill type Start\n");
        System.out.printf("To pay %.2f\n", getBill());
    }
}
