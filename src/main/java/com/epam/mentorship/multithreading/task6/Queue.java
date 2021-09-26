package com.epam.mentorship.multithreading.task6;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public abstract class Queue {
    private final int CAPACITY = 2;
    protected int cyclesCount = 0;
    private long endTime = 0;
    private long startTime = 0;

    public abstract void produce();

    public abstract void consume();

    public long getPerformance() {
        return cyclesCount / ((endTime - startTime) / 1000000000);
    }


    public boolean find1Or6(int[] array) {
        if (array.length == 0) {
            return false;
        }
        if (array[0] == 6 || array[array.length - 1] == 6) {
            return true;
        }
        return false;
    }
}
