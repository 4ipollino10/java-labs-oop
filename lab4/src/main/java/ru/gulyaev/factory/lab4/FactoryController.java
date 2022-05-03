package ru.gulyaev.factory.lab4;

import ru.gulyaev.factory.lab4.gear.Accessory;
import ru.gulyaev.factory.lab4.gear.Body;
import ru.gulyaev.factory.lab4.gear.Engine;
import ru.gulyaev.factory.lab4.task.SellCarTask;
import ru.gulyaev.factory.lab4.threadpool.ThreadPool;

import java.io.IOException;
import java.util.Properties;

public class FactoryController {
    private static final String CONFIG_PROPERTIES = "/config.properties";
    private static final String NUMBER_OF_DEALERS = "NumberOfDealers";
    private static final String NUMBER_OF_WORKERS = "NumberOfWorkers";
    private static final String NUMBER_OF_SUPPLIERS = "NumberOfSuppliers";
    private static final String CAR_BODY_STORAGE_CAPACITY = "CarBodyStorageCapacity";
    private static final String ENGINE_STORAGE_CAPACITY = "EngineStorageCapacity";
    private static final String ACCESSORIES_STORAGE_CAPACITY = "AccessoriesStorageCapacity";
    private static final String CAR_STORAGE_CAPACITY = "CarStorageCapacity";
    private static final String ACCESSORIES_SUPPLIERS = "Accessories Suppliers";
    private static final String ACCESSORIES_SUPPLIER = "Accessories Supplier";
    private static final String ENGINE_SUPPLIERS = "Engine Suppliers";
    private static final String ENGINE_SUPPLIER = "Engine Supplier";
    private static final String CAR_BODY_SUPPLIERS = "Car Body Suppliers";
    private static final String CAR_BODY_SUPPLIER = "Car Body Supplier";
    private static final String WORKERS = "Workers";
    private static final String WORKER = "Worker";
    private static final String DEALERS = "Dealers";
    private static final String DEALER = "Dealer";
    private static final int START_DELAY = 3000;

    private Properties _properties;

    private int _soldCarCounter;

    private Storage<Engine> _engineStorage;
    private Storage<Body> _carBodyStorage;
    private Storage<Accessory> _accessoriesStorage;
    private CarsStorage _carStorage;

    private final int _dealerCount;
    private final int _workerCount;
    private final int _accessoriesSupplierCount;

    private ThreadPool _accessoriesSupplierThreadPool;
    private ThreadPool _engineSupplierThreadPool;
    private ThreadPool _carBodySupplierThreadPool;
    private ThreadPool _workerThreadPool;
    private ThreadPool _dealerThreadPool;

    public ThreadPool getEngineSupplierThreadPool() {
        return _engineSupplierThreadPool;
    }

    public ThreadPool getAccessoriesSupplierThreadPool() {
        return _accessoriesSupplierThreadPool;
    }

    public ThreadPool getCarBodySupplierThreadPool() {
        return _carBodySupplierThreadPool;
    }

    public ThreadPool getWorkerThreadPool() {
        return _workerThreadPool;
    }

    public ThreadPool getDealerThreadPool() {
        return _dealerThreadPool;
    }

    public FactoryController() {
        try {
            _properties = new Properties();
            _properties.load(this.getClass().getResourceAsStream(CONFIG_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }

        _dealerCount = Integer.parseInt(_properties.getProperty(NUMBER_OF_DEALERS));
        _workerCount = Integer.parseInt(_properties.getProperty(NUMBER_OF_WORKERS));
        _accessoriesSupplierCount = Integer.parseInt(_properties.getProperty(NUMBER_OF_SUPPLIERS));

        createThreadPools();
        createStorages();
    }

    private void createStorages() {
        _carBodyStorage = new Storage<>(Integer.parseInt(_properties.getProperty(CAR_BODY_STORAGE_CAPACITY)), _carBodySupplierThreadPool, Body.class);
        _engineStorage = new Storage<>(Integer.parseInt(_properties.getProperty(ENGINE_STORAGE_CAPACITY)), _engineSupplierThreadPool ,Engine.class);
        _accessoriesStorage = new Storage<>(Integer.parseInt(_properties.getProperty(ACCESSORIES_STORAGE_CAPACITY)), _accessoriesSupplierThreadPool, Accessory.class);
        _carStorage = new CarsStorage(Integer.parseInt(_properties.getProperty(CAR_STORAGE_CAPACITY)), _workerThreadPool ,_accessoriesStorage, _carBodyStorage, _engineStorage);
    }

    private void createThreadPools() {
        _accessoriesSupplierThreadPool = new ThreadPool(_accessoriesSupplierCount, ACCESSORIES_SUPPLIERS, ACCESSORIES_SUPPLIER, START_DELAY);
        _engineSupplierThreadPool = new ThreadPool(1, ENGINE_SUPPLIERS, ENGINE_SUPPLIER, START_DELAY);
        _carBodySupplierThreadPool = new ThreadPool(1, CAR_BODY_SUPPLIERS, CAR_BODY_SUPPLIER, START_DELAY);
        _workerThreadPool = new ThreadPool(_workerCount, WORKERS, WORKER, START_DELAY);
        _dealerThreadPool = new ThreadPool(_dealerCount, DEALERS, DEALER, START_DELAY);
    }


    public void stopFactory() {
        _workerThreadPool.shutdown();
        _accessoriesSupplierThreadPool.shutdown();
        _engineSupplierThreadPool.shutdown();
        _carBodySupplierThreadPool.shutdown();
        _dealerThreadPool.shutdown();
    }

    public void sellCar() {
        _soldCarCounter++;
    }

    public long getSoldCarCounter() {
        return _soldCarCounter;
    }

    public Storage<Engine> getEngineStorage() {
        return _engineStorage;
    }

    public Storage<Body> getCarBodyStorage() { return _carBodyStorage; }

    public Storage<Accessory> getAccessoriesStorage() {
        return _accessoriesStorage;
    }

    public CarsStorage getCarStorage() {
        return _carStorage;
    }

    public int getDealerCount() {
        return _dealerCount;
    }

    public int getWorkerCount() {
        return _workerCount;
    }

    public int getAccessoriesSupplierCount() {
        return _accessoriesSupplierCount;
    }

    public void addSellTask() {
        _dealerThreadPool.addTask(new SellCarTask(this, _carStorage));
    }



}
