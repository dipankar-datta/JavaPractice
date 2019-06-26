package com.example.multithreading;

public class ResolvedDeadLockExample {


    public static void main(String[] args) {
        ResolvedDeadLockExample example = new ResolvedDeadLockExample();

        final ResourceA resourceA = example.new ResourceA();
        final ResourceB resourceB = example.new ResourceB();

        Runnable block1 = new Runnable() {
            @Override
            public void run() {
                /* In order to resolve the dead lock we have to change to sequence of lock,
                 * Here in the case we should lock resourceB first and then resourceA.
                 * Ideally locks in resources should be in same sequence throughout the application  */
                synchronized (resourceA) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (resourceB) {
                        System.out.println("In Block 1");
                    }
                }
            }
        };

        Runnable block2 = new Runnable() {
            @Override
            public void run() {
                synchronized (resourceB) {
                    synchronized (resourceA) {
                        System.out.println("In Block 2");
                    }
                }
            }
        };

        new Thread(block1).start();
        new Thread(block2).start();
    }

    private class ResourceA {
        private int i = 10;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    private class ResourceB {
        private int i = 20;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }
}
