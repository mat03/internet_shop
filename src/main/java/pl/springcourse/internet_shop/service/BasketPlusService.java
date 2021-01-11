package pl.springcourse.internet_shop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.springcourse.internet_shop.model.Basket;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@Profile("plus")
public class BasketPlusService {
    @Value("${tax.value}")
    private BigDecimal tax;

    private Basket basket;

    BasketPlusService()
    {
        basket = new Basket(5);
    }

    public BigDecimal getBill() {
        BigDecimal billWithTax = basket.getBill();

        BigDecimal totalTaxes = new BigDecimal(String.valueOf(billWithTax));
        totalTaxes = totalTaxes.multiply(tax);
        totalTaxes = totalTaxes.divide(BigDecimal.valueOf(100), RoundingMode.UP);

        billWithTax = billWithTax.add(totalTaxes);

        return billWithTax;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void viewBill() {
        System.out.println("\nBill type Plus\n");
        System.out.printf("To pay %.2f\n", getBill());
    }
}
