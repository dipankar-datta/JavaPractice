package com.example.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiExecutorUsage {
    public static void main(String[] args) {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
        RejectedExecutionHandler rejectionHandler = new RejectedExecutionHandlerImple();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 10, TimeUnit.SECONDS, workQueue, rejectionHandler);
        executor.prestartAllCoreThreads();
        List<Runnable> taskGroup = new ArrayList<Runnable>();
        taskGroup.add(new Test1());
        taskGroup.add(new Test2());
        taskGroup.add(new Test3());

        workQueue.add(new MultiRunnable(taskGroup));
    }

}

class RejectedExecutionHandlerImple implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString() + " : I have been rejected");
    }
}

class Test1 implements Runnable {
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

class MultiRunnable implements Runnable {
    private final List<Runnable> runnables;

    public MultiRunnable(List<Runnable> runnables) {
        this.runnables = runnables;
    }


    @Override
    public void run() {
        for (Runnable r : runnables){
            new Thread(r).start();
        }
    }
}

class Test2 implements Runnable {
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

class Test3 implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("Executing task three");
            try {
                Thread.sleep(1000);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }
}
