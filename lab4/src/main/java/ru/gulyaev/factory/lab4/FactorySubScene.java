package ru.gulyaev.factory.lab4;

import javafx.scene.SubScene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;

public class FactorySubScene extends SubScene {
    private final static int SCENE_WIDTH = 300;
    private final static int SCENE_HEIGHT = 40;
    private ProgressIndicator _progressIndicator;

    public FactorySubScene(){
        super(new AnchorPane(), SCENE_WIDTH, SCENE_HEIGHT);

        prefWidth(SCENE_WIDTH);
        prefHeight(SCENE_HEIGHT);

    }

    public FactorySubScene(ProgressIndicator indicator){
        super(new AnchorPane(), SCENE_WIDTH, SCENE_HEIGHT);

        prefWidth(SCENE_WIDTH);
        prefHeight(SCENE_HEIGHT);

        _progressIndicator = indicator;

    }

    public ProgressIndicator getProgressIndicator(){
        return _progressIndicator;
    }

}
