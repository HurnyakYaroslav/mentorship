package com.epam.mentorship.multithreading.task4;

/*
Create simple object pool with support for multithreaded environment. No any extra inheritance,
polymorphism or generics needed here, just implementation of simple class:
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Pool that block when it has not any items or it full
 */
public class BlockingObjectPool {
    private Queue<Object> objectQueue;
    private int MAX_SIZE;

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
    public Object get() {
        return objectQueue.isEmpty() ? null : objectQueue.poll();
    }

    /**
     * Puts object to pool or blocks if pool is full * * @param object to be taken back to pool
     */
    public void take(Object object) {
        if (objectQueue.size() < MAX_SIZE - 1) {
            objectQueue.add(object);
        }
    }
}

