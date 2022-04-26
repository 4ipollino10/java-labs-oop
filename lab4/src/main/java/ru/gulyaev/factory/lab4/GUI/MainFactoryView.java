package ru.gulyaev.factory.lab4.GUI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ru.gulyaev.factory.lab4.FactoryController;

import java.util.Timer;
import java.util.TimerTask;

public class MainFactoryView {
    private static final int SCENE_WIDTH = 1500;
    private static final int SCENE_HEIGHT = 800;
    private static final int WORKERS_SS_X = 648;
    private static final int WORKERS_SS_Y = 162;
    private static final int WORKERS_MIN = 0;
    private static final int WORKERS_MAX = 6000;
    private static final int WORKERS_S_X = 700;
    private static final int WORKERS_S_Y = 125;
    private static final int ACC_SS_X = 648;
    private static final int ACC_SS_Y = 495;
    private static final int ACC_MIN = 0;
    private static final int ACC_MAX = 6000;
    private static final int ACC_S_X = 700;
    private static final int ACC_S_Y = 455;
    private static final int DEAL_SS_X = 1097;
    private static final int DEAL_SS_Y = 400;
    private static final int DEAL_MIM = 0;
    private static final int DEAL_MAX = 6000;
    private static final int DEAL_S_X = 1150;
    private static final int DEAL_S_Y = 360;
    private static final int SHIFT = 1;
    private static final int BS_X = 50;
    private static final int BS_Y_SHIFT = 105;
    private static final String DEALER = "Dealer ";
    private static final String WORKER = "Worker ";
    private static final String ACCESSORY_SUPPLIER = "Accessory Supplier ";
    private static final String BODY_SUPPLIER = "body supplier";
    private static final String BODY_STORE = "body storage";
    private static final String ENGINE_SUPPLIER = "engine supplier";
    private static final String ENGINE_STORE = "engine storage";
    private static final String ACCESSORIES_STORE = "accessories storage";
    private static final String READY_CAR_STORE = "ready car storage";
    private static final String CAR_STORE_CONTROLLER = "car storage controller";
    private static final String CARS_SOLD = "cars sold: ";
    private static final String PLUS = "+";
    private static final String BG = "bg.png";
    private static final int BUTTON_X = 1030;
    private static final int BUTTON_Y = 400;
    private static final int PERIOD = 300;
    private static final int BODY_S_X = 20;
    private static final int BODY_S_MIN = 0;
    private static final int BODY_S_MAX = 6000;
    private static final int BODY_ST_X = 292;
    private static final int BODY_ST_Y = 336;
    private static final int ENG_S_X = 20;
    private static final int ENG_S_MIN = 0;
    private static final int ENG_S_MAX = 6000;
    private static final int ENG_ST_X = 290;
    private static final int ENG_ST_Y = 169;
    private static final int ACC_ST_X = 290;
    private static final int ACC_ST_Y = 509;
    private static final int CAR_ST_X = 1233;
    private static final int CAR_ST_Y = 193;
    private static final int CAR_ST_C_X = 1233;
    private static final int CAR_ST_C_Y = 60;
    private static final int CAR_SOLD_L_X = 684;
    private static final int CAR_SOLD_L_Y = 40;


    private final AnchorPane _anchorPane = new AnchorPane();
    private final Stage _stage;


    private final FactoryController _factoryController;
    private final MainFactoryViewController _mainFactoryViewController;

    public MainFactoryView(Stage stage, FactoryController factoryController){
        _stage = stage;
        _factoryController = factoryController;
        stage.setScene(new Scene(_anchorPane,SCENE_WIDTH, SCENE_HEIGHT));
        _mainFactoryViewController = new MainFactoryViewController(_factoryController);
        createViewParts();
        _stage.show();

    }

    private void createViewParts(){
        createScrollSubScenes();
        createDefaultSubScenes();
        createBackground();
        addSellCarButton();
    }

    private void createScrollSubScenes(){
        ScrollSubScene workersSubScene = new ScrollSubScene(WORKERS_SS_X, WORKERS_SS_Y);
        createWorkers(workersSubScene);

        SubSceneSlider workersDelay = new SubSceneSlider(WORKERS_MIN, WORKERS_MAX);
        workersDelay.setLayoutX(WORKERS_S_X);
        workersDelay.setLayoutY(WORKERS_S_Y);
        workersDelay.valueProperty().addListener((observableValue, old_val, new_val) -> _mainFactoryViewController.changeBuildTaskDelay(new_val.intValue()));

        ScrollSubScene accessoriesSuppliersSubScene = new ScrollSubScene(ACC_SS_X, ACC_SS_Y);
        createAccessoriesSuppliers(accessoriesSuppliersSubScene);

        SubSceneSlider accessoriesDelay = new SubSceneSlider(ACC_MIN, ACC_MAX);
        accessoriesDelay.setLayoutX(ACC_S_X);
        accessoriesDelay.setLayoutY(ACC_S_Y);
        accessoriesDelay.valueProperty().addListener((observableValue, old_val, new_val) -> _mainFactoryViewController.changeSupplyAccessoriesDelay(new_val.intValue()));

        ScrollSubScene dealersSubScene = new ScrollSubScene(DEAL_SS_X, DEAL_SS_Y);
        createDealers(dealersSubScene);

        SubSceneSlider dealersDelay = new SubSceneSlider(DEAL_MIM, DEAL_MAX);
        dealersDelay.setLayoutX(DEAL_S_X);
        dealersDelay.setLayoutY(DEAL_S_Y);
        dealersDelay.valueProperty().addListener((observableValue, old_val, new_val) -> _mainFactoryViewController.changeSellCarDelay(new_val.intValue()));

        _anchorPane.getChildren().addAll(workersSubScene, accessoriesSuppliersSubScene, dealersSubScene, workersDelay, accessoriesDelay, dealersDelay);
    }

    private void createDefaultSubScenes(){
        DefaultSubScene bodySupplier = new DefaultSubScene(BODY_SUPPLIER, BODY_S_X, BODY_ST_Y, BODY_S_MIN, BODY_S_MAX);
        bodySupplier.addSliderListener((observableValue, old_val, new_val) -> _mainFactoryViewController.changeSupplyCarBodyDelay(new_val.intValue()));

        DefaultSubScene bodyStorage = new DefaultSubScene(BODY_STORE, BODY_ST_X, BODY_ST_Y);

        DefaultSubScene engineSupplier = new DefaultSubScene(ENGINE_SUPPLIER, ENG_S_X, ENG_ST_Y, ENG_S_MIN, ENG_S_MAX);
        engineSupplier.addSliderListener((observableValue, old_val, new_val) -> _mainFactoryViewController.changeSupplyEngineDelay(new_val.intValue()));

        DefaultSubScene engineStorage = new DefaultSubScene(ENGINE_STORE, ENG_ST_X, ENG_ST_Y);

        DefaultSubScene accessoriesStorage = new DefaultSubScene(ACCESSORIES_STORE, ACC_ST_X, ACC_ST_Y);

        DefaultSubScene carsStorage = new DefaultSubScene(READY_CAR_STORE, CAR_ST_X, CAR_ST_Y);

        InfoLabel carsStorageController = new InfoLabel(CAR_STORE_CONTROLLER);
        carsStorageController.setLayoutX(CAR_ST_C_X);
        carsStorageController.setLayoutY(CAR_ST_C_Y);

        InfoLabel carsSold = new InfoLabel(CARS_SOLD);
        carsSold.setLayoutX(CAR_SOLD_L_X);
        carsSold.setLayoutY(CAR_SOLD_L_Y);

        _anchorPane.getChildren().addAll(bodySupplier, bodyStorage, engineSupplier, engineStorage, accessoriesStorage, carsStorage, carsStorageController, carsSold);

        Timer upd = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    carsSold.setText(CARS_SOLD + _factoryController.getSoldCarCounter());
                    carsStorage.setProgress(_factoryController.getCarStorage().getOccupancy());
                    bodyStorage.setProgress(_factoryController.getCarBodyStorage().getOccupancy());
                    accessoriesStorage.setProgress(_factoryController.getAccessoriesStorage().getOccupancy());
                    engineStorage.setProgress(_factoryController.getEngineStorage().getOccupancy());
                });
            }
        };
        upd.schedule(task, 0, PERIOD);
    }

    private void addSellCarButton() {
        Button b = new Button(PLUS);
        b.setLayoutX(BUTTON_X);
        b.setLayoutY(BUTTON_Y);

        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                _mainFactoryViewController.addSellCarTask();
            }
        });

        _anchorPane.getChildren().add(b);
    }

    private void createBackground() {
        Image backgroundImage = new Image(BG, 0, 0, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        _anchorPane.setBackground(new Background(background));
    }

    private void createDealers(ScrollSubScene dealersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < _factoryController.getDealerCount(); i++) {
            DefaultSubScene defaultSubScene = new DefaultSubScene(DEALER + String.valueOf(i + SHIFT), BS_X, i * BS_Y_SHIFT);
            container.getChildren().add(defaultSubScene);
        }
        dealersSubScene.getPane().setContent(container);

    }

    private void createWorkers(ScrollSubScene workersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < _factoryController.getWorkerCount(); i++) {
            container.getChildren().add(new DefaultSubScene(WORKER + String.valueOf(i + SHIFT), BS_X, i * BS_Y_SHIFT));
        }
        workersSubScene.getPane().setContent(container);
    }

    private void createAccessoriesSuppliers(ScrollSubScene accessoriesSuppliersSubScene) {
        final AnchorPane container = new AnchorPane();
        for (int i = 0; i < _factoryController.getAccessoriesSupplierCount(); i++) {
            container.getChildren().add(new DefaultSubScene(ACCESSORY_SUPPLIER + String.valueOf(i + SHIFT), BS_X, i * BS_Y_SHIFT));
        }
        accessoriesSuppliersSubScene.getPane().setContent(container);
    }
}
