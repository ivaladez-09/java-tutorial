package com.platzi.javatest.fizzbuzz;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class FizzBuzzTest {

    FizzBuzz fizzBuzz;

    @Before
    public void setup(){
        fizzBuzz = new FizzBuzz();
    }

    @Test
    public void return_fizz_when_number_divisible_by_3() {
        assertEquals(FizzBuzz.FIZZ, fizzBuzz.fizzBuzz(3));
        assertEquals(FizzBuzz.FIZZ, fizzBuzz.fizzBuzz(6));
        assertEquals(FizzBuzz.FIZZ, fizzBuzz.fizzBuzz(9));
    }

    @Test
    public void return_buzz_when_number_divisible_by_5() {
        assertEquals(FizzBuzz.BUZZ, fizzBuzz.fizzBuzz(5));
        assertEquals(FizzBuzz.BUZZ, fizzBuzz.fizzBuzz(10));
        assertEquals(FizzBuzz.BUZZ, fizzBuzz.fizzBuzz(50));
    }

    @Test
    public void return_fizzbuzz_when_number_divisible_by_3_and_5() {
        assertEquals(FizzBuzz.FIZZBUZZ, fizzBuzz.fizzBuzz(15));
        assertEquals(FizzBuzz.FIZZBUZZ, fizzBuzz.fizzBuzz(30));
        assertEquals(FizzBuzz.FIZZBUZZ, fizzBuzz.fizzBuzz(60));
    }

    @Test
    public void return_same_number_when_number_is_not_divisible_by_3_or_5() {
        assertEquals("2", fizzBuzz.fizzBuzz(2));
        assertEquals("16", fizzBuzz.fizzBuzz(16));
        assertEquals("17", fizzBuzz.fizzBuzz(17));
    }
}