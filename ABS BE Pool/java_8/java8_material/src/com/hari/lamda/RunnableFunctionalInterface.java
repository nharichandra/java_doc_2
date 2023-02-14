package com.hari.lamda;

public class RunnableFunctionalInterface {

    public static void main(String[] args) {

        // Without lambda, Runnable implementation using anonymous class
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(" Runnable example without lamda expression....");
            }
        };
        Thread thread = new Thread(runnable);
        thread.run();

        // With lamda expression.
        Runnable withLamda = () -> System.out.println(" Runnable example with lamda expression....");
        withLamda.run();

    }
}
