package ru.gulyaev.factory.lab4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene;
        stage.setTitle("Hello!");
        //stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}