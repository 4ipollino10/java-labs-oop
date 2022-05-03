package ru.gulyaev.factory.lab4.threadpool;


import ru.gulyaev.factory.lab4.task.Task;

import static ru.gulyaev.factory.lab4.Main.log;

public class PooledThread extends Thread{
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