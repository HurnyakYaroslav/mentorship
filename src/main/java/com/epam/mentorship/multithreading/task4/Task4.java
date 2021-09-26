package com.epam.mentorship.multithreading.task4;

/*
Create simple object pool with support for multithreaded environment. No any extra inheritance,
polymorphism or generics needed here, just implementation of simple class:

 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class Task4 {
    public static void main(String[] args) throws InterruptedException {
        BlockingObjectPool<Integer> objectPool = new BlockingObjectPool(2);
        Runnable first = () -> objectPool.take(4);
        Runnable second = () -> objectPool.take(5);
        Runnable third = () -> objectPool.take(6);
        Runnable fourth = () -> objectPool.take(7);
        Runnable fifth = objectPool::get;
        Runnable sixth = objectPool::get;

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        executorService.submit(first);
        executorService.submit(second);
        executorService.submit(third);
        executorService.submit(fourth);
        executorService.submit(fifth);
        executorService.submit(sixth);

        Thread.sleep(8000);
        executorService.shutdown();
    }
}
