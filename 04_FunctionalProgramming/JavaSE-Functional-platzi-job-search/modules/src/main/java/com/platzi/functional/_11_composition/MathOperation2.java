package com.platzi.functional._11_composition;

import java.util.function.Function;

public class MathOperation2 {
    public static void main(String[] args) {
        Function<Integer, Integer> multiplyBy3 = x -> {
            System.out.println("Multiplicando x: " + x);
            return x * 3;
        };

        Function<Integer, Integer> add1MultiplyBy3 =
                multiplyBy3.compose(y -> {
                    System.out.println("Agregar 1 a :" + y);
                    return y + 1;
                });

        Function<Integer, Integer> andSquare =
                add1MultiplyBy3.andThen(x -> {
                    System.out.println("Esoy agregando " + x + " al cuadrado");
                    return x * x;
                });

        System.out.println(add1MultiplyBy3.apply(5) + "\n");
        System.out.println(andSquare.apply(3));

    }
}
