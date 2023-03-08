package Problems.AgileEngine;

import java.util.*;

// support@codility.com
/*
* This is a demo task.
* Write a function:
* class Solution { public int solution(int[] A); }
* that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
* For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
* Given A = [1, 2, 3], the function should return 4.
* Given A = [−1, −3], the function should return 1.
* Write an efficient algorithm for the following assumptions:
* N is an integer within the range [1..100,000];
* each element of array A is an integer within the range [−1,000,000..1,000,000].
* */
public class Demo {
    public static void main(String[] args) {
        int[] input = {1, 3, 6, 4, 1, 2};
        System.out.println(solution(input));
    }

    public static int solution(int[] A) {
        // Implement your solution here
        int minPositive = 1;
        Set<Integer> set = new LinkedHashSet<>();
        Arrays.sort(A);

        for (int a: A) {
            set.add(a);
        }

        int i = minPositive;
        while (set.contains(i)) {
            i++;
        }
        return i;
    }
}
