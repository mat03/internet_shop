package pl.springcourse.internet_shop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.springcourse.internet_shop.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
@Profile("plus")
public class BasketPlus implements Basket{
    @Value("${tax.value}")
    private BigDecimal tax;

    private final List<Product> productsBasket;

    BasketPlus()
    {
        productsBasket = setExampleBasket(5);
    }

    @Override
    public BigDecimal getBill() {
        BigDecimal bill = new BigDecimal(0);
        for (Product product : productsBasket) {
            bill = bill.add(product.getPrice());
        }

        BigDecimal totalTaxes = new BigDecimal(String.valueOf(bill));
        totalTaxes = totalTaxes.multiply(tax);
        totalTaxes = totalTaxes.divide(BigDecimal.valueOf(100), RoundingMode.UP);

        bill = bill.add(totalTaxes);

        return bill;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void viewBill() {
        System.out.println("\nBill type Plus\n");
        System.out.printf("To pay %.2f\n", getBill());
    }
}
