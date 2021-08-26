package com.epam.mentorship.multithreading.task3;

import java.util.Random;

/*
Implement message bus using Producer-Consumer pattern.

Implement asynchronous message bus. Do not use queue implementations from java.util.concurrent.
Implement producer, which will generate and post randomly messages to the queue.
Implement consumer, which will consume messages on specific topic and log to the console message payload.
(Optional) Application should create several consumers and producers that run in parallel.
 */
//todo consume messages on specific topic
public class Task3 {
    private static MessageQueue messageQueue = new MessageQueue();

    private static class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                String message = String.valueOf(new Random().nextInt());
                messageQueue.put(message);
                System.out.println("Add message: " + message);
            }
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("Get message: " + messageQueue.get());
            }
        }
    }

    public static void main(String[] args) {
        Thread readThread = new Thread(new Consumer());
        Thread writeThread = new Thread(new Producer());

        readThread.start();
        writeThread.start();
    }
}
