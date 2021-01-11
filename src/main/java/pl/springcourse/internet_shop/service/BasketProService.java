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
@Profile("pro")
public class BasketProService {
    @Value("${tax.value}")
    private BigDecimal tax;

    @Value("${discount.value}")
    private BigDecimal discount;

    private Basket basket;

    BasketProService()
    {
        basket = new Basket(5);
    }

    public BigDecimal getBill() {
        BigDecimal billWithTaxAndDiscount = basket.getBill();

        BigDecimal totalTaxes = new BigDecimal(String.valueOf(billWithTaxAndDiscount));
        totalTaxes = totalTaxes.multiply(tax);
        totalTaxes = totalTaxes.divide(BigDecimal.valueOf(100), RoundingMode.UP);
        billWithTaxAndDiscount = billWithTaxAndDiscount.add(totalTaxes);

        BigDecimal totalDiscount = new BigDecimal(String.valueOf(billWithTaxAndDiscount));
        totalDiscount = totalDiscount.multiply(discount);
        totalDiscount = totalDiscount.divide(BigDecimal.valueOf(100), RoundingMode.UP);
        billWithTaxAndDiscount = billWithTaxAndDiscount.subtract(totalDiscount);

        return billWithTaxAndDiscount;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void viewBill() {
        System.out.println("\nBill type Pro\n");
        System.out.printf("To pay %.2f\n", getBill());
    }
}
