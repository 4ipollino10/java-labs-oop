package ru.gulyaev.factory.lab4.GUI;

import ru.gulyaev.factory.lab4.FactoryController;

public class MainFactoryViewController {
    private final FactoryController _factoryController;

    public MainFactoryViewController(FactoryController factoryController) {
    _factoryController = factoryController;
    }

    public void changeBuildTaskDelay(int new_val) {
        _factoryController.getWorkerThreadPool().setDelay(new_val);
    }

    public void changeSupplyCarBodyDelay(int new_val) {
        _factoryController.getCarBodySupplierThreadPool().setDelay(new_val);
    }

    public void changeSupplyEngineDelay(int new_val) {
        _factoryController.getEngineSupplierThreadPool().setDelay(new_val);
    }

    public void changeSupplyAccessoriesDelay(int new_val) {
        _factoryController.getAccessoriesSupplierThreadPool().setDelay(new_val);
    }

    public void changeSellCarDelay(int new_val) {
        _factoryController.getDealerThreadPool().setDelay(new_val);
    }


    public void addSellCarTask() {
        _factoryController.addSellTask();
    }


}
