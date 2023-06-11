package com.scivantage.maxit.lotprocessor.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LotProcessor {

    public static void main(String[] args) throws InterruptedException {
        QueueService qs = new QueueService();
        qs.start(1, 10);
        qs.start(11, 20);
        qs.start(21, 30);
        qs.start(31, 40);
        qs.waiting();
    }
}

class QueueService {

    private static final Integer FIXED_THREAD = 5;
    private static final Integer CAPACITY_QUEUE = 10;

    private ThreadPoolExecutor executor =
            (ThreadPoolExecutor)Executors.newFixedThreadPool(FIXED_THREAD);
    private List<QueueWorker> workers = new ArrayList<>();
    // create object of LinkedBlockingQueue
    LinkedBlockingQueue<String> linkedQueue = new LinkedBlockingQueue<>(CAPACITY_QUEUE);

    void start(int start, int end) throws InterruptedException {
        for (int i = start; i <= end; i++) {
            linkedQueue.add("" + i);
            QueueWorker worker = new QueueWorker("" + i, linkedQueue);
            workers.add(worker);
            executor.execute(worker);
            TimeUnit.MILLISECONDS.sleep(500);
        }
        System.out.println("[Status] ExecutorQueueSize:" + executor.getQueue().size()+ "  Working threads in pool: " + executor.getPoolSize());//getWorkingThreads(workers)
        System.out.println("[Status] QueueSize:" + linkedQueue.size()+ "  Workers: " + getWorkingThreads(workers));//
        waitWorkers(workers);
        TimeUnit.SECONDS.sleep(1);
//		executor.purge();
        purgeStoppedWorkers(workers);
    }

    private void purgeStoppedWorkers(List<QueueWorker> workers) {
        List<QueueWorker> temp = new ArrayList<QueueWorker>(workers);
        for (QueueWorker worker : temp) {
            if (worker.isStopped()) {
                workers.remove(worker);
            }
        }
    }

    private void waitWorkers(List<QueueWorker> workers) throws InterruptedException {
        for (QueueWorker worker : workers) {
            worker.join();
        }
    }

    String getWorkingThreads(List<QueueWorker> workers) {
        String[] workingThreads = new String[workers.size()];
        for (int i = 0; i < workingThreads.length; i++) {
            workingThreads[i] = workers.get(i).getName();
        }
        return Arrays.toString(workingThreads);
    }

    void waiting() throws InterruptedException {
        while (!executor.isTerminated()) {
            TimeUnit.SECONDS.sleep(5);
            System.out.println("[Status] ExecutorQueueSize:" + executor.getQueue().size()+ "  Working threads in pool: " + executor.getPoolSize());//getWorkingThreads(workers)
            System.out.println("[Status] QueueSize:" + linkedQueue.size()+ "  Workers: " + getWorkingThreads(workers));//
        }
    }

}

class QueueWorker extends Thread {

    private String taskId;
    private LinkedBlockingQueue<String> linkedQueue;

    private volatile boolean exit = false;

    public QueueWorker(String taskId, LinkedBlockingQueue<String> linkedQueue) {
        this.taskId = taskId;
        this.linkedQueue = linkedQueue;
    }

    @Override
    public void run() {
        while (!isStopped()) {
            System.out
                    .println("[Status] QueueSize:" + linkedQueue.size() + " Thread: " + Thread.currentThread().getName()
                            + "[id:" + Thread.currentThread().hashCode() + "] Started. taskId = " + taskId);
            runTask();
            System.out.println("[Status] Thread: " + Thread.currentThread().getName() + " End. taskId = " + taskId);
        }
        System.out.println("=========================================================================\n");
    }

    private synchronized void runTask() {
        while (linkedQueue != null && !linkedQueue.isEmpty()) {
            String queueItem = linkedQueue.poll();
            process(queueItem);
        }
        exit();
    }

    private synchronized void process(String queueItem) {
        System.out.println("Processing message: " + queueItem + " by thread: " + Thread.currentThread().getName());
    }

    @Override
    public String toString() {
        return this.taskId;
    }

    public boolean isStopped() {
        return exit;
    }

    public void exit() {
        this.exit = true;
    }

}
