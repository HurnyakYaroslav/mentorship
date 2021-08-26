package com.epam.mentorship.multithreading.task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/*
Create HashMap<Integer, Integer>. The first thread adds elements into the map,
 the other go along the given map and sum the values. Threads should work before catching
 ConcurrentModificationException. Try to fix the problem with ConcurrentHashMap and Collections.synchronizedMap().
 What has happened after simple Map implementation exchanging? How it can be fixed in code? Try
 to write your custom ThreadSafeMap with synchronization and without. Run your samples with different versions
  of Java (6, 8, and 10, 11) and measure the performance.
 */
//todo write ThreadSafeMap

public class Task1WithException {

    private static Map<Integer, Integer> map = new HashMap<>();

    static class AddMapValuesThread implements Runnable {
        @Override
        public void run() {
            while(true){
                map.put(new Random().nextInt() * 100, (int) (Math.random() * 100));
            }

        }
    }

    static class SumMapValuesThread implements Runnable {
        private int getSum() {
            return map.values().stream()
                    .mapToInt(Integer::intValue)
                    .sum();
        }

        @Override
        public void run() {
            while (true){
                System.out.println(getSum());
            }
        }
    }

    public static void main(String[] args) {
        Thread modify = new Thread(new AddMapValuesThread());
        Thread sum = new Thread(new SumMapValuesThread());

        modify.start();
        sum.start();
    }
}
