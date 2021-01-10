package pl.springcourse.internet_shop.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.springcourse.internet_shop.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Profile("pro")
public class BasketPro implements Basket{
    @Value("${tax.value}")
    private BigDecimal tax;

    @Value("${discount.value}")
    private BigDecimal discount;

    private final List<Product> productsBasket;

    BasketPro()
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

        BigDecimal totalDiscount = new BigDecimal(String.valueOf(bill));
        totalDiscount = totalDiscount.multiply(discount);
        totalDiscount = totalDiscount.divide(BigDecimal.valueOf(100), RoundingMode.UP);
        bill = bill.subtract(totalDiscount);

        return bill;
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void viewBill() {
        System.out.println("\nBill type Pro\n");
        System.out.printf("To pay %.2f\n", getBill());
    }
}
