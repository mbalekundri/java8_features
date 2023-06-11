package com.java.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

// Java program to demonstrate
// poll(long timeout, TimeUnit unit)
// method of LinkedBlockingQueue


public class LinkedBlockingQueue3 {

    public static void main(String[] args)
            throws InterruptedException {
        // define capacity of LinkedBlockingQueue
        int capacityOfQueue = 4;

        // create object of LinkedBlockingQueue
        LinkedBlockingQueue<String> linkedQueue
                = new LinkedBlockingQueue<String>(capacityOfQueue);

        // Add element to LinkedBlockingQueue
        linkedQueue.add("Ravi");
        linkedQueue.add("Suraj");
        linkedQueue.add("Harsh");

        // print elements of queue
        System.out.println("Items in Queue are " + linkedQueue);

        // Try to poll elements from linkedQueue
        // using poll(long timeout, TimeUnit unit) method
        System.out.println("Removing item From head: "
                + linkedQueue.poll(5, TimeUnit.SECONDS));

        // print queue details
        System.out.println("Now Queue Contains" + linkedQueue);

        // using poll(long timeout, TimeUnit unit) method
        System.out.println("Removing item From head: "
                + linkedQueue.poll(5, TimeUnit.SECONDS));

        // print queue details
        System.out.println("Now Queue Contains" + linkedQueue);

        // using poll(long timeout, TimeUnit unit) method
        System.out.println("Removing item From head: "
                + linkedQueue.poll(5, TimeUnit.SECONDS));

        // print queue details
        System.out.println("Now Queue Contains" + linkedQueue);

        // using poll(long timeout, TimeUnit unit) method
        System.out.println("Removing item From head: "
                + linkedQueue.poll(5, TimeUnit.SECONDS));
        // print queue details
        System.out.println("Now Queue Contains" + linkedQueue);
    }
}
