package Problems.Lyft;

import java.util.Arrays;

/*
* Given an array of integers, return an array of integers such that the value at index i of the output
* is the product of everything in yhr input except yhr input value at index i. AN empty list should
* return []. A list of length 1 should return [1] no matter what tje input is.
*
* Examples:
* Input=[2, 3, 4, 5], output=[60, 40, 30, 24]
* Input=[], output=[]
* Input=[2], output=[1]
* */
public class ArrayOfIntegers {
    public static void main(String[] args) {
        int[][] arraysOfIntegers = {
                {2, 3, 4, 5},
                {},
                {4},
                {1, 2, 3, 0},
                {0, 0, 0, 0}
        };

        for (int[] arr: arraysOfIntegers) {
            Arrays.stream(getArrayOfProducts(arr))
                    .forEach(System.out::println);
            System.out.println("\n\n==============\n\n");
        }
    }

    public static int[] getArrayOfProducts(int[] arr) {
        if (arr.length == 0) {
            return new int[0];
        } else if (arr.length == 1) {
            return new int[]{1};
        }

        int[] products = new int[arr.length];

        int totalProduct = 1;
        for (int i = 0; i < arr.length; i++) {
            totalProduct *= arr[i];
        }

        if (totalProduct == 0) {
            for (int i = 0; i < arr.length; i++) {
                products[i] = 0;
            }
            return products;
        }

        for (int i = 0; i < arr.length; i++) {
            products[i] = totalProduct / arr[i];
        }

        return products;
    }
}
