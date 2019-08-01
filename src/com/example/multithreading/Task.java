package com.example.multithreading;

class Task implements Runnable {

    private String name;

    Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        for (int i = 1; i <=5; i++) {
            try {
                System.out.println("XXX---> Running Thread :: " + name + " :: Count -> " + i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }
}
