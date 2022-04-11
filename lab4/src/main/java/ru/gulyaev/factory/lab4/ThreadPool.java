package ru.gulyaev.factory.lab4;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final int _threads;
    private final LinkedBlockingQueue _queue;

    public ThreadPool(int threads){
        _threads = threads;
        _queue = new LinkedBlockingQueue();
    }



}
