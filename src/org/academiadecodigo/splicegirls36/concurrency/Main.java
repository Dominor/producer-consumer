package org.academiadecodigo.splicegirls36.concurrency;

import org.academiadecodigo.splicegirls36.concurrency.bqueue.BQueue;

public class Main {

    public static void main(String[] args) {



        BQueue<Pizza> queue= new BQueue<>(10);

        Producer p1 = new Producer(queue,10);
        Thread t1 = new Thread(p1);
        t1.setName("Mario");

        Producer p2 = new Producer(queue,10);
        Thread t2 = new Thread(p2);
        t2.setName("Luigi");

        Consumer c1 = new Consumer(queue, 10);
        Thread t3 = new Thread(c1);
        t3.setName("Donatello");

        Consumer c2 = new Consumer(queue, 10);
        Thread t4 = new Thread(c2);
        t4.setName("Michelangelo");

        t3.start();
        t4.start();

        t1.start();
        t2.start();

    }

}

