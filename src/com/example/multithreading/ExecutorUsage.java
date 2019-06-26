package com.example.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorUsage {

    private static ExecutorService executor = null;
    private static volatile Future taskOneResult = null;
    private static volatile Future taskTwoResult = null;

    public static void main(String[] args) {
        executor = Executors.newFixedThreadPool(2);
        while (true) {
            try {
                checkTasks();
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Caught exception : " + e.getMessage());
            }
        }
    }

    private static void checkTasks() throws Exception {
        if (taskOneResult == null
            || taskOneResult.isDone()
            || taskOneResult.isCancelled()) {
            taskOneResult = executor.submit( new TestOne());
        }
        if (taskTwoResult == null
                || taskTwoResult.isDone()
                || taskTwoResult.isCancelled()) {
            taskTwoResult = executor.submit( new TestTwo());
        }
    }
}

class TestOne implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("Executing task one");
            try {
                Thread.sleep(1000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}

class TestTwo implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("Executing task two");
            try {
                Thread.sleep(1000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
