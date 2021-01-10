package pl.springcourse.internet_shop.model;

import java.math.BigDecimal;
import java.util.Random;

public class BigDecimalRandomDataGenerator {
    final int minLimit;
    final int maxLimit;

    public BigDecimalRandomDataGenerator(int minLimit, int maxLimit) {
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
    }

    public BigDecimal getRandomData()
    {
        double generatedDouble = minLimit + new Random().nextDouble() * (maxLimit - minLimit);

        return new BigDecimal(generatedDouble);
    }
}
