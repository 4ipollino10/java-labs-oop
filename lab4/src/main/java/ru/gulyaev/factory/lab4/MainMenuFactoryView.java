package ru.gulyaev.factory.lab4;

import javafx.scene.SubScene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainMenuFactoryView extends View {
    private final ArrayList<SubScene> _subScenes;




    public MainMenuFactoryView(Stage stage){
        super(stage);
        _subScenes = new ArrayList<>();
    }
}
