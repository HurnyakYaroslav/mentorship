package com.epam.mentorship.multithreading.task6.implementation;

import com.epam.mentorship.multithreading.task6.Queue;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.PriorityBlockingQueue;

@Slf4j
public class BlockingQueue extends Queue {

    private final java.util.Queue<Integer> queue;


    public BlockingQueue() {
        queue = new PriorityBlockingQueue<>(getCAPACITY());
    }

    public void produce() {
        setStartTime(System.nanoTime());
        while (true) {
            int value = (int) (Math.random() * 100);
            queue.add(value);
            cyclesCount++;
            if (Thread.currentThread().isInterrupted()) {
                log.warn("Thread {} was stopped manually.", Thread.currentThread().getName());
                break;
            }
        }
        setEndTime(System.nanoTime());
    }

    public void consume() {
        while (true) {
            Integer result = queue.poll();
            if (Thread.currentThread().isInterrupted()) {
                log.warn("Thread {} was stopped manually.", Thread.currentThread().getName());
                break;
            }
        }
    }
}
