package com.platzi.functional._15_streams_intro;

import java.util.stream.IntStream;

public class TyepStream {
    public static void main(String[] args) {
        IntStream infiniteStream = IntStream.iterate(0, x -> x + 1);
        Long t1 = System.nanoTime();
        infiniteStream.limit(1000)
                //.parallel() // Reparte la carga en los hilos disponibles
                .filter(x -> x % 2 == 0)
                .map(x -> x*2);
        System.out.println("Execution time: " + ((System.nanoTime() - t1)/100000));
    }
}
