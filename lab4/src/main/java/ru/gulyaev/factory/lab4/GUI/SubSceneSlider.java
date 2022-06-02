package ru.gulyaev.factory.lab4.GUI;

import javafx.scene.SubScene;
import javafx.scene.control.Slider;

public class SubSceneSlider extends Slider {
    private static final int BLOCK_INCREMENT = 100;
    private static final int MAJOR_TICK_UNIT = 1000;
    private static final int MINOR_TICK_COUNT = 9;
    private static final int WIDTH = 230;
    private static final int HEIGHT = 25;

    public SubSceneSlider(){
        setShowTickMarks(true);
        setShowTickLabels(true);
        setBlockIncrement(BLOCK_INCREMENT);
        setMajorTickUnit(MAJOR_TICK_UNIT);
        setMinorTickCount(MINOR_TICK_COUNT);
        setSnapToTicks(true);

        setPrefSize(WIDTH, HEIGHT);
    }

    public SubSceneSlider(int min, int max) {
        this();
        setMin(min);
        setMax(max);
        setValue(max);
    }
}
