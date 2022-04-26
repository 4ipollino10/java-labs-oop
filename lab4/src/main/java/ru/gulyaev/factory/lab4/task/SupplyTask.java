package ru.gulyaev.factory.lab4.task;

import lombok.extern.slf4j.Slf4j;
import ru.gulyaev.factory.lab4.Storage;
import ru.gulyaev.factory.lab4.gear.Gear;
import ru.gulyaev.factory.lab4.gear.Product;
import ru.gulyaev.factory.lab4.thredpool.Constants;

@Slf4j
public class SupplyTask<T extends Product & Gear> implements Task {
    private static final String SUPPLIED = " supplied ";

    private final Storage<T> storage;


    private final Class<T> productType;

    public SupplyTask(Storage<T> storage, Class<T> productType) {
        this.storage = storage;
        this.productType = productType;
    }

    @Override
    public void doWork(String threadName, int delay) throws InterruptedException {
        try {
            Thread.sleep(delay);
            T product = productType.getDeclaredConstructor().newInstance();
            storage.put(product);
            log.info(threadName + SUPPLIED + product.getFullVin());
        } catch (InterruptedException e) {
            log.info(threadName + Constants.INTERRUPTED);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
}