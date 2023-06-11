package com.java.thread;

import java.util.concurrent.LinkedBlockingQueue;

// Java Program Demonstrate poll()
// method of LinkedBlockingQueue
public class LinkedBlockingQueue2 {

    public void pollingMethod() {
        // define capacity of LinkedBlockingQueue
        int capacityOfQueue = 5;

        // create object of LinkedBlockingQueue
        LinkedBlockingQueue<Employee> linkedQueue = new LinkedBlockingQueue<Employee>(capacityOfQueue);

        // Add element to LinkedBlockingQueue
        Employee emp1 = new Employee("emp1", "Tester", "39000");
        Employee emp2 = new Employee("emp2", "Manager", "98000");
        Employee emp3 = new Employee("emp3", "Tester", "39000");
        Employee emp4 = new Employee("emp4", "Manager", "98000");
        Employee emp5 = new Employee("emp5", "Tester", "39000");
        Employee emp6 = new Employee("emp6", "Manager", "98000");

        // Add Employee Objects to linkedQueue
        linkedQueue.add(emp1);
        linkedQueue.add(emp2);
        linkedQueue.add(emp3);
        linkedQueue.add(emp4);
        linkedQueue.add(emp5);

        // remove elements from the queue
        // and follow this process again and again
        // until the queue becomes empty
        while (linkedQueue.size() != 0) {

            // Remove Employee item from LinkedBlockingQueue
            Employee removedEmp = linkedQueue.poll();

            // print removedItem
            System.out.println("Removed Item is :");
            System.out.println("Employee Name - " + removedEmp.name);
            System.out.println("Employee Position - " + removedEmp.position);
            System.out.println("Employee Salary - " + removedEmp.salary);

            // find size  of linkedQueue
            int size = linkedQueue.size();

            // print remaining capacity value
            System.out.println("\nSize of list :" + size + "\n");
        }

        // Now size of Queue is Zero
        // Now try to Poll more items
        // Remove Employee item from LinkedBlockingQueue
        Employee removedEmp = linkedQueue.poll();

        // print removedItem
        // size is zero so removedItem is null
        System.out.println("Removed Item is : " + removedEmp);
    }

    // create an Employee Object with name,
    // position and salary as an attribute
    public class Employee {

        public String name;
        public String position;
        public String salary;

        Employee(String name, String position, String salary) {
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee [name=" + name + ", position=" + position + ", salary=" + salary + "]";
        }
    }

    // Main Method
    public static void main(String[] args) {
        LinkedBlockingQueue2 gfg = new LinkedBlockingQueue2();
        gfg.pollingMethod();
    }
}
