package ru.gulyaev.factory.lab4.task;

import lombok.extern.slf4j.Slf4j;
import ru.gulyaev.factory.lab4.CarsStorage;
import ru.gulyaev.factory.lab4.Storage;
import ru.gulyaev.factory.lab4.gear.Accessory;
import ru.gulyaev.factory.lab4.gear.Body;
import ru.gulyaev.factory.lab4.gear.Car;
import ru.gulyaev.factory.lab4.gear.Engine;
import ru.gulyaev.factory.lab4.thredpool.Constants;

@Slf4j
public class ProduceCarTask implements Task {
    private static final String PRODUCED_CAR = " produced car: ";


    private final Storage<Accessory> _accessoriesStorage;
    private final Storage<Body> _carBodyStorage;
    private final Storage<Engine> _engineStorage;
    private final CarsStorage _carStorage;


    public ProduceCarTask(Storage<Accessory> accessoriesStorage, Storage<Body> carBodyStorage, Storage<Engine> engineStorage, CarsStorage carStorage) {

        _accessoriesStorage = accessoriesStorage;
        _carBodyStorage = carBodyStorage;
        _engineStorage = engineStorage;
        _carStorage = carStorage;
    }

    @Override
    public void doWork(String threadName, int delay) throws InterruptedException {
        try {
            Thread.sleep(delay);
            Body carBody = _carBodyStorage.get();
            Engine engine = _engineStorage.get();
            Accessory accessories = _accessoriesStorage.get();
            Car newCar = new Car(carBody, engine, accessories);
            _carStorage.put(newCar);
            log.info(threadName + PRODUCED_CAR + newCar.getFullVin() + newCar.getProductID());
        } catch (InterruptedException e) {
            log.info(threadName + Constants.INTERRUPTED);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
