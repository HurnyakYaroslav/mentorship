package com.epam.mentorship.multithreading.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


/*
Create HashMap<Integer, Integer>. The first thread adds elements into the map,
 the other go along the given map and sum the values. Threads should work before catching
 ConcurrentModificationException. Try to fix the problem with ConcurrentHashMap and Collections.synchronizedMap().
 What has happened after simple Map implementation exchanging? How it can be fixed in code? Try
 to write your custom ThreadSafeMap with synchronization and without. Run your samples with different versions
  of Java (6, 8, and 10, 11) and measure the performance.
 */
@Slf4j
public class Task1Implementation {
    private static final int COUNT = 1000;
    private static Map<Integer, Integer> map;

    public void configureHashMapExecution(){
        long startTime = System.nanoTime();
            map = new HashMap<>();
        execute();
        long endTime = System.nanoTime();
        log.info("Duration executeHashMap: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    public void configureConcurrentHashMapExecution(){
        long startTime = System.nanoTime();
        map = new ConcurrentHashMap<>();
        execute();
        long endTime = System.nanoTime();
        log.info("Duration executeConcurrentHashMap: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    public void configureSynchronizedMapExecution(){
        long startTime = System.nanoTime();
        map = Collections.synchronizedMap(new HashMap<>());
        execute();
        long endTime = System.nanoTime();
        log.info("Duration executeSynchronizedHashMap: " + (endTime - startTime) + " milliseconds : " + new Date());

    }

    public void configureCustomSynchronizedThreadSafeMapMapExecution(){
        long startTime = System.nanoTime();
        map = new CustomSynchronizedThreadSafeMap<>();
        execute();
        long endTime = System.nanoTime();
        log.info("Duration executeSynchronizedHashMap: " + (endTime - startTime) + " milliseconds : " + new Date());
    }

    public void configureCustomThreadSafeMapMapExecution(){
        long startTime = System.nanoTime();
        map = new CustomThreadSafeMap<>();
        execute();
        long endTime = System.nanoTime();
        log.info("Duration executeSynchronizedHashMap: " + (endTime - startTime) + " milliseconds : " + new Date());

    }

    static class AddMapValuesThread implements Runnable {
        @Override
        public void run() {
            log.info("Start 'AddElements' thread: {}", Thread.currentThread().getName());
            for (int i = 0; i < COUNT; i++) {
                map.put(new Random().nextInt() * 100, (int) (Math.random() * 100));
            }
            log.info("End 'AddElements' thread: {}", Thread.currentThread().getName());

        }
    }

    static class SumMapValuesThread implements Runnable {
        private int getSum() {
            return map.values().stream()
                    .reduce(Integer::sum)
                    .orElse(0);
        }

        @Override
        public void run() {
            log.info("Start 'SumElements' thread: {}", Thread.currentThread().getName());
            for (int i = 0; i < COUNT / 3; i++) {
                log.info("sum: {}", getSum());
            }
            log.info("End 'SumElements' thread: {}", Thread.currentThread().getName());
        }
    }

    private void execute(){
        Thread modify = new Thread(new AddMapValuesThread());
        Thread sum = new Thread(new SumMapValuesThread());

        modify.start();
        sum.start();

        try {
            modify.join();
            sum.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Task1Implementation obj = new Task1Implementation();
        obj.configureConcurrentHashMapExecution();
    }
}
