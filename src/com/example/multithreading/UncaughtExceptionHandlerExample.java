package com.example.multithreading;

class MyTask implements Runnable {
    @Override
    public void run() {
        Thread.currentThread().setUncaughtExceptionHandler(new ExceptionHandler());
        System.out.println(Integer.parseInt("123"));
        System.out.println(Integer.parseInt("555"));
        System.out.println(Integer.parseInt("685"));
        System.out.println(Integer.parseInt("879"));
        System.out.println(Integer.parseInt("ABC"));
        System.out.println(Integer.parseInt("963"));
    }
}

class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("An exception has been captured\n");
        System.out.printf("Thread: %s\n", t.getId());
        System.out.printf("Exception: %s: %s\n", t.getClass().getName(), e.getMessage());
        System.out.printf("Stack trace: \n");
        e.printStackTrace(System.out);
        System.out.printf("Thread status: %s\n", t.getState());
        new Thread(new MyTask()).start();
    }
}

public class UncaughtExceptionHandlerExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyTask());
        thread.start();
    }
}
