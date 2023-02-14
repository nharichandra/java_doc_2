package com.java8learning.lambdas;

/*
author : Nagarjuna Reddy Pothireddy
 */
public class RunnableLambdaExample {

    public static void main(String[] args) {

        /**
         * Lagacy Imperative style code
         */

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside Runnable with Lagacy Style");
            }
        };
        new Thread(runnable).start();

        // Another way of Implementation in Lagacy
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Inside Runnable with Lagacy Style : 2");
            }
        }).start();

        /**
         *  Declaration with Lambda Expression
         */
        // With Single statement in method body. { is not required
        Runnable runnable1 = () -> System.out.println("Inside Runnable with Lambdas");
        new Thread(runnable1).start();

        //With 2 lines of code which required { braces
        Runnable runnable2 = () -> {
            System.out.println("Inside Runnable with Lambdas : 1.1");
            System.out.println("Inside Runnable with Lambdas : 1.2");
        };
        new Thread(runnable2).start();

        // Another way of implementation with Lambdas
        new Thread(() -> System.out.println("Another Way of Implement using Lambdas")).start();


    }
}
