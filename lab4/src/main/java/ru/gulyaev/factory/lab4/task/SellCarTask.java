package ru.gulyaev.factory.lab4.task;

import org.apache.log4j.Logger;
import ru.gulyaev.factory.lab4.utils.CarsStorage;
import ru.gulyaev.factory.lab4.factory.FactoryController;
import ru.gulyaev.factory.lab4.gear.Car;
import ru.gulyaev.factory.lab4.threadpool.Constants;


public class SellCarTask implements Task {
    public static final Logger log = Logger.getLogger(SellCarTask.class);
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
