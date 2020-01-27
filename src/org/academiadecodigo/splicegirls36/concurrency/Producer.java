package org.academiadecodigo.splicegirls36.concurrency;

import org.academiadecodigo.splicegirls36.concurrency.bqueue.BQueue;

import java.util.Random;

/**
 * Produces and stores integers into a blocking queue
 */
public class Producer implements Runnable {

    private final BQueue<Pizza> queue;
    private int pizzasToMake;

    /**
     * @param queue the blocking queue to add elements to
     * @param elementNum the number of elements to produce
     */
    public Producer(BQueue queue, int elementNum) {
        this.queue = queue;
        this.pizzasToMake = elementNum;
    }

    @Override
    public void run() {

        int counter = 0;
        while(counter < pizzasToMake) {

            Pizza pizza = new Pizza();
            System.out.println(Thread.currentThread().getName() + " made a very nice " + pizza.toString() + ".");

            synchronized (queue) {
                queue.offer(pizza);
                if (queue.getSize() == queue.getLimit()) {
                    System.out.println(Thread.currentThread().getName() + ": Mamma Mia, Enough work for me!");
                }
            }

            System.out.println("PIZZA ADDED, COUNTER CONTAINS: " + ++counter + " PIZZAS");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
