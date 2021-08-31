package com.epam.mentorship.multithreading.task6;

import com.epam.mentorship.multithreading.task6.implementation.BlockingQueue;
import com.epam.mentorship.multithreading.task6.implementation.LinkedListQueue;
import lombok.extern.slf4j.Slf4j;

/**
 * Create a multi-threading console application that starts two threads for producer and consumer respectively.
 * It does not matter what kind of data it produces/consumes (e.g. producer could generate random numbers and consumer could calculate their total average).
 * There must be a graceful shutdown (use Runtime.getRuntime().addShutdownHook(), Object's join()/interrupt() methods) to allow threads to correctly finish their work.
 * When both producer and consumer are stopped print to console how many operations were performed per second (ops/sec).
 * <p>
 * This task should be implemented using two approaches:
 * <p>
 * Classic model: use non-blocking Queue implementation (e.g. LinkedList) to share data between producer and consumer threads use synchronized block, wait()/notify() methods to guard the queue from simultaneous access.
 * Concurrency use classes from java.util.concurrent package for synchronization (BlockingQueue, locks, etc.).
 * When both versions are done compare their performance (ops/sec).
 */
@Slf4j
public class Task6 {
    public static void main(String[] args) throws InterruptedException {
        Queue queue = new LinkedListQueue();
        Thread producer = new Thread(queue::produce);
        Thread consumer = new Thread(queue::consume);
        consumer.start();
        producer.start();

        Thread.sleep(3000);
        producer.interrupt();
        consumer.interrupt();
        Thread.sleep(10);
        log.info("LinkedListQueue performance: {}", queue.getPerformance());

        queue = new BlockingQueue();
        producer = new Thread(queue::produce);
        consumer = new Thread(queue::consume);
        consumer.start();
        producer.start();

        Thread.sleep(3000);
        producer.interrupt();
        consumer.interrupt();
        Thread.sleep(10);
        log.info("BlockingQueue performance: {}", queue.getPerformance());
    }
}
