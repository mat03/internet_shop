package pl.springcourse.internet_shop.service;

import pl.springcourse.internet_shop.model.BigDecimalRandomDataGenerator;
import pl.springcourse.internet_shop.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface Basket {
    BigDecimal getBill();

    void viewBill();

    default List<Product> setExampleBasket(int elementNumberInBasket)
    {
        List<Product> exampleBasket = new ArrayList<>();

        BigDecimalRandomDataGenerator bigDecimalRandomDataGenerator = new BigDecimalRandomDataGenerator(50, 300);

        for(int idx = 0; idx < elementNumberInBasket; ++idx)
        {
            String name = "Product_" + idx;
            exampleBasket.add(new Product(name, bigDecimalRandomDataGenerator.getRandomData()));
        }

        return exampleBasket;
    }

}
