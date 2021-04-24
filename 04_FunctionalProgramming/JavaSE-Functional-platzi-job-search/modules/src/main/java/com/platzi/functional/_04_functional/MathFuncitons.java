package com.platzi.functional._04_functional;

import java.util.function.Function;
import java.util.function.Predicate;

public class MathFuncitons {
    public static void main(String[] args) {
        Function<Integer, Integer> square = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x*x;
            }
        };
        System.out.println(square.apply(5));

        Function<Integer, Boolean> isOdd = x -> x % 2 == 1;

        Predicate<Integer> isEven = x -> x % 2 == 0;

        System.out.println(isOdd.apply(4));
        System.out.println(isEven.test(4));

        Predicate<Student> isApproved = student -> student.getQualification() >= 6;
        Student ivan = new Student(5.9);

        System.out.println(isApproved.test(ivan));
    }
}

class Student {
    private double qualification;

    Student(double qualification) {
        this.qualification = qualification;
    }

    public double getQualification() {
        return qualification;
    }

    }
