package ru.gulyaev.factory.lab4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private CopyTask copyTask;

    @Override
    public void start(Stage stage) throws IOException {

        ProgressIndicator progressIndicator = new ProgressIndicator(0);
        progressIndicator.setProgress(0);
        copyTask = new CopyTask();
        progressIndicator.progressProperty().unbind();

        // Bind progress property.
        float i = -1;
        float maxi = 100;
        while(i != maxi){
            i++;
            for(int j = 0; j < 100; j++) {
                progressIndicator.setProgress(i / maxi);
            }
        }

        FlowPane anchorPane = new FlowPane();

        anchorPane.setPadding(new Insets(10));
        anchorPane.setHgap(10);

        anchorPane.getChildren().add(progressIndicator);

        Scene scene = new Scene(anchorPane, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
