package ru.gulyaev.factory.lab4.task;

import org.apache.log4j.Logger;
import ru.gulyaev.factory.lab4.utils.Storage;
import ru.gulyaev.factory.lab4.gear.Gear;
import ru.gulyaev.factory.lab4.gear.Product;
import ru.gulyaev.factory.lab4.threadpool.Constants;


public class SupplyTask<T extends Product & Gear> implements Task {
    public static final Logger log = Logger.getLogger(SupplyTask.class);

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