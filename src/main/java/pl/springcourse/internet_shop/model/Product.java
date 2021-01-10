package pl.springcourse.internet_shop.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price)
    {
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPrice()
    {
        return this.price;
    }
}
