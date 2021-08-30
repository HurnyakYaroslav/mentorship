package com.epam.mentorship.multithreading.task3;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class MessageQueue {

    private final int CAPACITY = 2;

    private final Queue<String> queue = new LinkedList<>();

    private final Object monitor = new Object();

    public void produce(String value) throws InterruptedException {
        synchronized (monitor) {
            if (queue.isEmpty()) {
                monitor.wait();
            }
            queue.add(value);
            log.info("Add value: {}", value);
            monitor.notifyAll();
        }
    }

    public String consume() throws InterruptedException {
        synchronized (monitor) {
            if (queue.size() >= CAPACITY) {
                monitor.wait();
            }
            String result = queue.poll();
            log.info("Poll value: {}", result);
            monitor.notifyAll();
            return result;
        }
    }
}
