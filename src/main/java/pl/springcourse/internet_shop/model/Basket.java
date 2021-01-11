package pl.springcourse.internet_shop.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private BigDecimal bill;
    private List<Product> productList;

    public Basket(int elementNumberInBasket)
    {
        bill = new BigDecimal(0);
        productList = new ArrayList<>();

        CustomNumberUtils customNumberUtils = new CustomNumberUtils(50, 300);

        for(int idx = 0; idx < elementNumberInBasket; ++idx)
        {
            String name = "Product_" + idx;
            BigDecimal randomPrice = customNumberUtils.getRandomData();

            productList.add(new Product(name, randomPrice));

            bill = bill.add(randomPrice);
        }
    }

    public BigDecimal getBill() {
        return bill;
    }

    public List<Product> getProductList() {
        return productList;
    }

}
