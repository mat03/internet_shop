package pl.springcourse.internet_shop.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.springcourse.internet_shop.model.Product;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("start")
public class BasketStart implements Basket{
    private final List<Product> productsBasket;

    BasketStart()
    {
        productsBasket = setExampleBasket(5);
    }

    @Override
    public BigDecimal getBill() {

        BigDecimal bill = new BigDecimal(0);
        for (Product product : productsBasket) {
            bill = bill.add(product.getPrice());
        }

        return bill;
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void viewBill() {
        System.out.println("\nBill type Start\n");
        System.out.printf("To pay %.2f\n", getBill());
    }
}
