package com.epam.mentorship.multithreading.task6;

import lombok.Getter;
import lombok.Setter;

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
}
