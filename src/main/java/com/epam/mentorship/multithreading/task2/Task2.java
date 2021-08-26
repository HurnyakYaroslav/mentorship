package com.epam.mentorship.multithreading.task2;

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

public class Task2 {

    private static List<Integer> list = new ArrayList<>();

    private static synchronized int getSum() {
        return list.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static synchronized double getSumOfSquares() {
        return list.stream()
                .mapToDouble(Integer::doubleValue)
                .map(i -> i * i)
                .sum();
    }

    private static synchronized double getRootOfSquaresSum() {
        return Math.sqrt(getSumOfSquares());
    }

    private static synchronized void addValue() {
        list.add(new Random().nextInt());
    }

    private static void sleep() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


    private static class WritingThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                addValue();
                sleep();
            }
        }
    }

    private static class PrintSumThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("Sum: " + getSum());
                sleep();
            }
        }
    }

    private static class PrintSquaresThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("Root of Squares sum: " + getRootOfSquaresSum());
                sleep();
            }
        }
    }

    public static void main(String[] args) {
        Thread write = new Thread(new WritingThread());
        Thread printSum = new Thread(new PrintSumThread());
        Thread printRoot = new Thread(new PrintSquaresThread());

        write.start();
        printSum.start();
        printRoot.start();
    }
}
