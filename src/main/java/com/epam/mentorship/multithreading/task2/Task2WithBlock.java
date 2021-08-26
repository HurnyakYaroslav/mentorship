package com.epam.mentorship.multithreading.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task2WithBlock {
    private static List<Integer> list = new ArrayList<>();
    private static Object monitor = new Object();

    private static int getSum() {
        synchronized (monitor) {
            return list.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
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
            return Math.sqrt(getSumOfSquares());
        }
    }

    private static void addValue() {
        synchronized (monitor) {
            list.add(new Random().nextInt());
        }
    }


    private static class WritingThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                addValue();
            }
        }
    }

    private static class PrintSumThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("Sum: " + getSum());
            }
        }
    }

    private static class PrintSquaresThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("Root of Squares sum: " + getRootOfSquaresSum());
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
