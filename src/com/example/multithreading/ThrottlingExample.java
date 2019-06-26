package com.example.multithreading;

import java.util.concurrent.*;

class ThrottlingTask implements Runnable {

    private String name = null;

    public ThrottlingTask(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Executing: " + name);
    }
}

class CustomThreadPoolxecutor extends ThreadPoolExecutor {


     public CustomThreadPoolxecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
     }

     @Override
     protected void beforeExecute(Thread t, Runnable r) {
         super.beforeExecute(t, r);
     }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t != null) {
            t.printStackTrace();
        }
    }
}

class BlockingThreadPoolExecutor extends ThreadPoolExecutor {
    private Semaphore semaphore;

    public BlockingThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        this.semaphore = new Semaphore(corePoolSize + 50);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
    }

    @Override
    public void execute(final Runnable task) {
        boolean acquired = false;

        do {
            try {
                semaphore.acquire();
                acquired = true;
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("Exception while acquiring semaphore");
            }
        } while (!acquired);

        try {
            super.execute(task);
        } catch (final RejectedExecutionException e) {
            System.out.println("Tasl rejected");
            semaphore.release();
            throw  e;
        }
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        if (t != null) {
            t.printStackTrace();
        }
        semaphore.release();
    }


}

class ThrottlingExecutor {
    public static void main(String[] args) {
        Integer threadCounter = 0;
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(50);
        //ThreadPoolExecutor executor = new CustomThreadPoolxecutor(10, 20, 5000, TimeUnit.MILLISECONDS, blockingQueue);
        ThreadPoolExecutor executor = new BlockingThreadPoolExecutor(10, 20, 5000, TimeUnit.MILLISECONDS, blockingQueue);
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("ThrottlingTask rejected : " + ((ThrottlingTask) r).getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Lets add another time : " + ((ThrottlingTask) r).getName());
                executor.execute(r);
            }
        });
        executor.prestartAllCoreThreads();
        while (true) {
            threadCounter++;
            executor.execute(new ThrottlingTask(threadCounter.toString()));
            if (threadCounter == 1000) {
                break;
            }
        }
    }
}



public class ThrottlingExample {
}
