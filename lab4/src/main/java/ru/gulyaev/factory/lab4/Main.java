package ru.gulyaev.factory.lab4;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ru.gulyaev.factory.lab4.GUI.MainFactoryView;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FactoryController factoryController = new FactoryController();
        MainFactoryView view = new MainFactoryView(stage, factoryController);
    }

    public static void main(String[] args) {
        launch();
    }
}
