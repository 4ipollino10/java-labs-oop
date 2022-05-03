package ru.gulyaev.factory.lab4;


import ru.gulyaev.factory.lab4.gear.Accessory;
import ru.gulyaev.factory.lab4.gear.Body;
import ru.gulyaev.factory.lab4.gear.Car;
import ru.gulyaev.factory.lab4.gear.Engine;
import ru.gulyaev.factory.lab4.task.ProduceCarTask;
import ru.gulyaev.factory.lab4.threadpool.ThreadPool;

import java.util.ArrayDeque;

import static ru.gulyaev.factory.lab4.Main.log;

public class CarsStorage {
    public static final String STORAGE_WAS_INTERRUPTED = "Storage was interrupted!";

    private final ArrayDeque<Car> _gear;
    private final int _storageCapacity;
    private final ThreadPool _threadPool;

    private final Storage<Accessory> _accessoriesStorage;
    private final Storage<Body> _carBodyStorage;
    private final Storage<Engine> _engineStorage;


    public CarsStorage(int capacity, ThreadPool threadPool, Storage<Accessory> accessoriesStorage, Storage<Body> carBodyStorage, Storage<Engine> engineStorage) {
        _storageCapacity = capacity;
        _threadPool = threadPool;

        _accessoriesStorage = accessoriesStorage;
        _carBodyStorage = carBodyStorage;
        _engineStorage = engineStorage;

        _gear = new ArrayDeque<>();
        for (int i = 0; i < capacity; i++) {
            threadPool.addTask(new ProduceCarTask(accessoriesStorage, carBodyStorage, engineStorage, this));
        }
    }

    public double getOccupancy() {
        return _gear.size() / (double) _storageCapacity;
    }

    public int getStorageCapacity() {
        return _storageCapacity;
    }

    public synchronized Car get() throws InterruptedException {
        while (_gear.size() < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.info(STORAGE_WAS_INTERRUPTED);
                throw e;
            }
        }
        Car car = _gear.removeLast();
        _threadPool.addTask(new ProduceCarTask(_accessoriesStorage, _carBodyStorage, _engineStorage, this));
        notify();
        return car;
    }

    public synchronized void put(Car car) {
        while (_gear.size() >= _storageCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                log.info(STORAGE_WAS_INTERRUPTED);
            }
        }

        _gear.add(car);
        notify();
    }
}