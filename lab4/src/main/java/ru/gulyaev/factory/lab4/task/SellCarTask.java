package ru.gulyaev.factory.lab4.task;

import ru.gulyaev.factory.lab4.CarsStorage;
import ru.gulyaev.factory.lab4.FactoryController;
import ru.gulyaev.factory.lab4.gear.Car;
import ru.gulyaev.factory.lab4.threadpool.Constants;

import static ru.gulyaev.factory.lab4.Main.log;

public class SellCarTask implements Task {

    private static final String CAR = "Car ";
    private static final String CONGRATULATIONS = " SOLD! Congratulations to the ";
    private static final String GOOD_JOB = " GOOD JOB!";


    private final FactoryController factoryController;

    private final CarsStorage storage;

    public SellCarTask(FactoryController factoryController, CarsStorage storage) {
        this.factoryController = factoryController;
        this.storage = storage;
    }

    @Override
    public void doWork(String threadName, int delay) {
        try {
            Thread.sleep(delay);
            Car carForSale = storage.get();
            factoryController.sellCar();
            log.info(CAR + carForSale.getFullVin() + CONGRATULATIONS + threadName + GOOD_JOB);
        } catch (InterruptedException e) {
            log.info(threadName + Constants.INTERRUPTED);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
