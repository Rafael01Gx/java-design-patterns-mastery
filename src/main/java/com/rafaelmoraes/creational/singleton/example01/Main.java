package com.rafaelmoraes.creational.singleton.example01;

import java.util.concurrent.CountDownLatch;

public class Main {
    static void main() throws InterruptedException {
        int numThreads = 50;
        CountDownLatch readyL = new CountDownLatch(numThreads);
        CountDownLatch startL = new CountDownLatch(1);

        for(int i = 0; i < numThreads; i++){

            new Thread(new Runnable(){

                @Override
                public void run() {
                    readyL.countDown();

                    try {
                        startL.await();
                        DatabaseConnection instance = DatabaseConnection.getInstance();
                        IO.println("Instance: " + instance.hashCode());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();

        }
        readyL.await();
        startL.countDown();
    }
}
