package org.academiadecodigo.splicegirls36.concurrency.bqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Blocking Queue
 * @param <T> the type of elements stored by this queue
 */
public class BQueue<T> {

    private Queue<T> queue;
    private int limit;

    /**
     * Constructs a new queue with a maximum size
     * @param limit the queue size
     */
    public BQueue(int limit) {

        queue = new LinkedList<>();
        this.limit = limit;
    }

    /**
     * Inserts the specified element into the queue
     * Blocking operation if the queue is full
     * @param data the data to add to the queue
     */
    public synchronized void offer(T data) {

        if (data == null) {

            return;
        }

        while (queue.size() == limit) {
            try {
                System.out.println(Thread.currentThread().getName() + ": Waiting - Full Queue!");
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        queue.offer(data);

        notifyAll();
        System.out.println(Thread.currentThread().getName() + ": Waking up - Normal Queue!");
    }

    /**
     * Retrieves and removes data from the head of the queue
     * Blocking operation if the queue is empty
     * @return the data from the head of the queue
     */
    public synchronized T poll() {

        T result = null;

        while (queue.isEmpty()) {

            try {
                System.out.println(Thread.currentThread().getName() + ": Waiting - Empty Queue!");
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        result = queue.poll();
        notifyAll();

        System.out.println(Thread.currentThread().getName() + ": Waking up - Normal Queue!");

        return result;
    }

    /**
     * Gets the number of elements in the queue
     * @return the number of elements
     */
    public int getSize() {

        return queue.size();

    }

    /**
     * Gets the maximum number of elements that can be present in the queue
     * @return the maximum number of elements
     */
    public int getLimit() {

        return limit;

    }

}
