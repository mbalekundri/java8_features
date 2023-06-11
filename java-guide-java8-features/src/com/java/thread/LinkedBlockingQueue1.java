package com.java.thread;


import java.util.concurrent.LinkedBlockingQueue;
//https://www.geeksforgeeks.org/linkedblockingqueue-poll-method-in-java/?ref=lbp

public class LinkedBlockingQueue1 {

    public static void main(String[] args)
    {
        // define capacity of LinkedBlockingQueue
        int capacityOfQueue = 4;

        // create object of LinkedBlockingQueue
        LinkedBlockingQueue<String> linkedQueue
                = new LinkedBlockingQueue<String>(capacityOfQueue);

        // Add element to LinkedBlockingQueue
        linkedQueue.add("1");
        linkedQueue.add("2");
        linkedQueue.add("3");
        linkedQueue.add("4");

        // print elements of queue
        System.out.println("Items in Queue are " + linkedQueue);

        // we want to remove two elements from queue from head
        // Applying poll() method on queue to remove element
        String removedItem1 = linkedQueue.poll();

        // print removedItem and queue
        System.out.println("Removed Item is " + removedItem1);

        // print elements of queue after removing first item
        System.out.println("Remaining Items in Queue are "
                + linkedQueue);

        // Applying poll() method on queue to remove another element
        String removedItem2 = linkedQueue.poll();

        // print removedItem and queue
        System.out.println("Removed Item is " + removedItem2);

        // print elements of queue after removing first item
        System.out.println("Remaining Items in Queue are " + linkedQueue);
    }
}
