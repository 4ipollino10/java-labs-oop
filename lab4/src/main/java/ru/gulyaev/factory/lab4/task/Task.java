package ru.gulyaev.factory.lab4.task;

public interface Task {
    void doWork(String threadName, int delay) throws InterruptedException;
}
