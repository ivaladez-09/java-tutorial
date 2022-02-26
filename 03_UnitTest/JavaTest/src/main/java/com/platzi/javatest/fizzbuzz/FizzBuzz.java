package com.platzi.javatest.fizzbuzz;

public class FizzBuzz {

    public static String FIZZ = "Fizz";
    public static String BUZZ = "Buzz";
    public static String FIZZBUZZ = "FizzBuzz";

    public String fizzBuzz(int n) {
        String result = String.valueOf(n);

        if (n % 3 == 0){
            result = FIZZ;
            if (n % 5 == 0){
                result = FIZZBUZZ;
            }
        }
        else if (n % 5 == 0) {
            result = BUZZ;
        }

        return result;
    }
}
