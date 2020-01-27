package org.academiadecodigo.splicegirls36.concurrency;

import org.academiadecodigo.splicegirls36.concurrency.bqueue.BQueue;

/**
 * Consumer of integers from a blocking queue
 */
public class Consumer implements Runnable {

    private final BQueue<Pizza> queue;
    private int pizzasToConsume;

    /**
     * @param queue the blocking queue to consume elements from
     * @param elementNum the number of elements to consume
     */
    public Consumer(BQueue queue, int elementNum) {
        this.queue = queue;
        this.pizzasToConsume = elementNum;
    }

    @Override
    public void run() {

        int counter = pizzasToConsume;
        Pizza pizza;

        while (counter > 0) {

            synchronized (queue) {
                pizza = queue.poll();
                if(queue.getSize() == 0) {
                    System.out.println(Thread.currentThread().getName() + ": Mamma Mia, No more pizzas for me.");
                }
            }
            System.out.println("PIZZA REMOVED, COUNTER CONTAINS: " + --counter + " PIZZAS");
            System.out.println(Thread.currentThread().getName() + " ate a delicious " + pizza.toString() + ".");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
