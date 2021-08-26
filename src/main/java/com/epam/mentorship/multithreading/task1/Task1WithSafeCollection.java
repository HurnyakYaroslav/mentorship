package com.epam.mentorship.multithreading.task1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Task1WithSafeCollection {
//    private static Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());
    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

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
        Thread modify = new Thread(new Task1WithException.AddMapValuesThread());
        Thread sum = new Thread(new Task1WithException.SumMapValuesThread());

        modify.start();
        sum.start();
    }
}
