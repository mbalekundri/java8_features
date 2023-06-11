package com.java.thread;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Thread.currentThread;

public class Main {
    static class ThreadPool {
        Queue<Runnable> tasks = new LinkedList<>();

        public ThreadPool() {
            Thread mainWorker = new Thread(() -> {
                while (true) {
                    Runnable newTaskToBeExecuted = tasks.poll();
                    if (newTaskToBeExecuted != null) newTaskToBeExecuted.run();
                }
            }, "Main Worker");
            mainWorker.start();
        }

        public void execute(Runnable newTask) {
            tasks.add(newTask);
        }
    }

    public static void main(String[] args) {
        Runnable firstTask = () -> System.out.println("first task running in thread: " + currentThread().getName());
        Runnable secondTask = () -> System.out.println("second task running in thread: " + currentThread().getName());

        ThreadPool mySingleThreadPool = new ThreadPool();
        mySingleThreadPool.execute(firstTask);
        mySingleThreadPool.execute(secondTask);
    }
}
