package ru.gulyaev.factory.lab4;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public abstract class View {

    public static final double HEIGHT = 768.0;
    private static final double WIDTH = 1024.0;
    private final Stage _stage;

    public View(Stage stage) {
        _stage = stage;
    }

    public Stage getStage() {
        return _stage;
    }

    public void setNewScene(AnchorPane anchorPane) {
        _stage.setScene(new Scene(anchorPane, WIDTH, HEIGHT));
    }

    public void show() {
        _stage.show();
    }
}
