package com.java8learning.lambdas;

import java.util.Comparator;

public class ComparatorLambdaExample {

    public static void main(String[] args) {

        /**
         * Old Style of Code
         */
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2); // 0 -> o1 == o2
                                         // 1 -> o1 > o2
                                        // -1 -> o1 < o2
            }
        };
        System.out.println("Comparator using old style of code :"+ comparator.compare(10,9));


        /**
         * Lambda Style of Code
         */
        // For Single statement , doesn't require return statement.
        // Input type also not required. Java8 Can automatically detects the type of the Input based on the return value
        Comparator<Integer> comparator1 = (a,b) -> a.compareTo(b);
        System.out.println("Comparator using Lambda Style :"+comparator1.compare(10,9));
    }
}
