package com.epam.mentorship.multithreading.task6.implementation;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
public class LinkedListQueue extends com.epam.mentorship.multithreading.task6.Queue {

    private final Queue<Integer> queue;

    private final Object monitor;

    public LinkedListQueue() {
        queue = new LinkedList<>();
        monitor = new Object();
    }

    public void produce() {
        setStartTime(System.nanoTime());
        while (true) {
            synchronized (monitor) {
                if (queue.size() >= getCAPACITY()) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        log.warn("Thread {} was stopped manually.", Thread.currentThread().getName());
                        break;
                    }
                }
                int value = (int) (Math.random() * 100);
                queue.add(value);
                cyclesCount++;
//                log.info("Added value: {}", value);
                monitor.notifyAll();
                if (Thread.currentThread().isInterrupted()) {
                    log.warn("Thread {} was stopped manually.", Thread.currentThread().getName());
                    setEndTime(System.nanoTime());
                    break;
                }
            }
        }
        log.info("End time set");
        setEndTime(System.nanoTime());
    }

    public void consume() {
        while (true) {
            synchronized (monitor) {
                if (queue.isEmpty()) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
//                log.info("Poll value: {}", result);
                monitor.notifyAll();
                if (Thread.currentThread().isInterrupted()) {
                    log.warn("Thread {} was stopped manually.", Thread.currentThread().getName());
                    setEndTime(System.nanoTime());
                    break;
                }
            }
        }
    }


}
