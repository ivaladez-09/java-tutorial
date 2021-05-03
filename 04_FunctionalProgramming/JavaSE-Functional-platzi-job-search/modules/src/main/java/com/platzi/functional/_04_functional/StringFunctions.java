package com.platzi.functional._04_functional;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class StringFunctions {
    public static void main(String[] args) {
        UnaryOperator<String> quote = text -> "\"" + text + "\"";

        System.out.println(quote.apply("Hola a todos"));

        BiFunction<Integer, Integer, Integer> multiplicacion = (x, y) -> x * y;
        BinaryOperator<Integer> multiplicacion2 = (x, y) -> x * y;

        BiFunction<String, Integer, String> leftpad = (text, number) -> String.format("%" + number + "s", text);
        System.out.println(leftpad.apply("Java", 10));
    }
}
