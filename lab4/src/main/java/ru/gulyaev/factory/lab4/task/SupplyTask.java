package ru.gulyaev.factory.lab4.task;

import ru.gulyaev.factory.lab4.Storage;
import ru.gulyaev.factory.lab4.gear.Gear;
import ru.gulyaev.factory.lab4.gear.Product;
import ru.gulyaev.factory.lab4.threadpool.Constants;

import static ru.gulyaev.factory.lab4.Main.log;

public class SupplyTask<T extends Product & Gear> implements Task {
    private static final String SUPPLIED = " supplied ";

    private final Storage<T> _storage;


    private final Class<T> _productType;

    public SupplyTask(Storage<T> storage, Class<T> productType) {
        _storage = storage;
        _productType = productType;
    }

    @Override
    public void doWork(String threadName, int delay) {
        try {
            Thread.sleep(delay);
            T product = _productType.getDeclaredConstructor().newInstance();
            _storage.put(product);
            log.info(threadName + SUPPLIED + product.getFullVin());
        } catch (InterruptedException e) {
            log.info(threadName + Constants.INTERRUPTED);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }
}