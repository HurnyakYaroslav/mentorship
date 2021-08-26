package com.epam.mentorship.multithreading.task3;

import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue {
    private Queue<String> queue = new LinkedList<>();

    public synchronized void put(String message){
        queue.add(message);
    }

    public synchronized String get(){
       return queue.poll();
    }
}
