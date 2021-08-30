package com.epam.mentorship.multithreading.task2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Create three threads:
1st thread is infinitely writing random number to the collection;
2nd thread is printing sum of the numbers in the collection;
3rd is printing square root of sum of squares of all numbers in the collection.
Make these calculations thread-safe using synchronization block. Fix the possible deadlock.
 */
//todo clarify about full synchronizing
@Slf4j
public class Task2WithBlock {
    private static List<Integer> list = new ArrayList<>();
    private static Object monitor = new Object();

    private static int getSum() {
        synchronized (monitor) {
            int sum = 0;
            try {
                sum = list.stream()
                        .mapToInt(Integer::intValue)
                        .sum();
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sum;
        }
    }

    private static double getSumOfSquares() {
        return list.stream()
                .mapToDouble(Integer::doubleValue)
                .map(i -> i * i)
                .sum();
    }

    private static double getRootOfSquaresSum() {
        synchronized (monitor) {
            double sqrt = 0;
            monitor.notifyAll();
            try {
                sqrt = Math.sqrt(getSumOfSquares());
                monitor.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sqrt;
        }
    }

    private static void addValue() {
        synchronized (monitor) {
            monitor.notifyAll();
            try {
                monitor.wait();
                list.add(new Random().nextInt());
                log.warn("Value added");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Thread write = new Thread(() -> {
            while (true) {
                addValue();
            }
        });
        Thread printSum = new Thread(() -> {
            while (true) {
                log.info("Sum: " + getSum());
            }
        });
        Thread printRoot = new Thread(() -> {
            while (true) {
                log.info("Root of Squares sum: " + getRootOfSquaresSum());
            }
        });

        printSum.start();
        printRoot.start();
        write.start();
    }
}
