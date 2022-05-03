package ru.gulyaev.factory.lab4;

import javafx.application.Application;
import org.apache.log4j.Logger;
import javafx.stage.Stage;
import ru.gulyaev.factory.lab4.GUI.MainFactoryView;

import java.io.IOException;

public class Main extends Application {
    public static final Logger log = Logger.getLogger(Main.class);
    public static final String START_MESSAGE = "Let's sell some cars!";

    @Override
    public void start(Stage stage) throws IOException {
        log.info(START_MESSAGE);
        FactoryController factoryController = new FactoryController();
        new MainFactoryView(stage, factoryController);


    }

    public static void main(String[] args) {
        launch();
    }
}
