package com.epam.mentorship.multithreading.task3;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/*
Implement message bus using Producer-Consumer pattern.

Implement asynchronous message bus. Do not use queue implementations from java.util.concurrent.
Implement producer, which will generate and post randomly messages to the queue.
Implement consumer, which will consume messages on specific topic and log to the console message payload.
(Optional) Application should create several consumers and producers that run in parallel.
 */
//todo consume messages on specific topic
@Slf4j
public class Task3 {
    private static MessageQueue messageQueue = new MessageQueue();

    public static void main(String[] args) {
        Thread readThread = new Thread(() -> {
            while (true) {
                try {
                    log.warn("Consumer: {}", messageQueue.consume());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread writeThread = new Thread(() -> {
            while (true) {
                try {
                    log.warn("Producer");
                    messageQueue.produce(String.valueOf(new Random().nextInt()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        readThread.start();
        writeThread.start();

    }
}
