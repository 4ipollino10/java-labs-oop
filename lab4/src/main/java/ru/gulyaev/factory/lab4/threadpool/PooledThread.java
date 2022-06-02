package ru.gulyaev.factory.lab4.threadpool;


import org.apache.log4j.Logger;

import ru.gulyaev.factory.lab4.task.Task;


public class PooledThread extends Thread{
    public static final Logger log = Logger.getLogger(PooledThread.class);
    private boolean shutdownFlag = false;

    private final ThreadPool threadPool;


    public PooledThread(String name, ThreadPool threadPool) {
        super(name);
        this.threadPool = threadPool;
    }

    public void interruptPooledThread() {
        shutdownFlag = true;
        interrupt();
    }

    private void performTask(Task t) {
        try {
            t.doWork(getName(), threadPool.getDelay());
        } catch (InterruptedException e) {
            log.info(getName() + Constants.INTERRUPTED);
            shutdownFlag = true;
        }
    }

    public void run() {
        try {
            while (!shutdownFlag) {
                performTask(threadPool.getTask());
            }
        } catch (InterruptedException e) {
            interruptPooledThread();
            log.info(getName() + Constants.INTERRUPTED);
        }
    }
}