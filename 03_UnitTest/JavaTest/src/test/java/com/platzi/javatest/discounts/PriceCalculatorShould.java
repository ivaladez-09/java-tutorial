package com.platzi.javatest.discounts;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PriceCalculatorShould {

    PriceCalculator priceCalculator;

    @Before
    public void setup(){
        priceCalculator = new PriceCalculator();
    }

    @Test
    public void total_zero_when_there_are_prices() {
        assertEquals(0.0, priceCalculator.getTotal(), 0);
    }

    @Test
    public void total_is_the_sum_of_prices() {
        priceCalculator.addPrice(10);
        priceCalculator.addPrice(15);

        assertEquals(25.0, priceCalculator.getTotal(), 0);
    }

    @Test
    public void apply_discount_to_prices() {
        priceCalculator.addPrice(50);
        priceCalculator.addPrice(50);
        priceCalculator.setDiscount(50);

        assertEquals(50.0, priceCalculator.getTotal(), 0);
    }
}