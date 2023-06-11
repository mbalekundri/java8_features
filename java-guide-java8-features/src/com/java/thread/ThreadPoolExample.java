package com.java.thread;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//https://www.geeksforgeeks.org/linkedblockingqueue-poll-method-in-java/?ref=lbp

class WorkerThread implements Runnable {

    private String task;

    public WorkerThread(String s){
        this.task=s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. task = "+task);
        processTask();
        System.out.println(Thread.currentThread().getName()+" End.");
    }

    private void processTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        return this.task;
    }
}

public class ThreadPoolExample {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 1; i <= 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}
