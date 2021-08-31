package com.epam.mentorship.multithreading.task4;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Pool that block when it has not any items or it full
 */
@Slf4j
public class BlockingObjectPool<T> {

    private final int MAX_SIZE;

    private final Queue<T> objectQueue;


    /**
     * Creates filled pool of passed size * * @param size of pool
     */
    public BlockingObjectPool(int size) {
        objectQueue = new LinkedList<>();
        MAX_SIZE = size;
    }

    /**
     * Gets object from pool or blocks if pool is empty * * @return object from pool
     */
    public synchronized T get() {
        if (objectQueue.isEmpty()) {
            try {
                log.info("Get request is waiting");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T response = objectQueue.poll();
        log.info("{} was polled", response);
        this.notifyAll();
        return response;
    }

    /**
     * Puts object to pool or blocks if pool is full * * @param object to be taken back to pool
     */
    public synchronized void take(T object) {
        if (objectQueue.size() == MAX_SIZE) {
            try {
                log.info("Take request is waiting");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        objectQueue.add(object);
        log.info("{} was taken", object);
        this.notifyAll();
    }
}

