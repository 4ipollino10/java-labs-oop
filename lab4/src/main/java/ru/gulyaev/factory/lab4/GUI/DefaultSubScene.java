package ru.gulyaev.factory.lab4.GUI;

import javafx.beans.value.ChangeListener;
import javafx.scene.SubScene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

public final class DefaultSubScene extends SubScene {
    private static final int WIDTH = 240;
    private static final int HEIGHT = 105;
    private static final int LAYOUT_X = 5;
    private static final int LABEL_LAYOUT_Y = 45;
    private static final int BAR_LAYOUT_Y = 38;
    private static final int PREF_WIDTH = 230;
    private static final int PREF_HEIGHT = 15;

    private final SubSceneSlider _slider = new SubSceneSlider();
    private final InfoLabel _label = new InfoLabel();
    private final ProgressBar _bar = new ProgressBar();

    public DefaultSubScene(String name, double x, double y) {
        super(new AnchorPane(), WIDTH, HEIGHT);

        _label.setText(name);
        _label.setLayoutX(LAYOUT_X);
        _label.setLayoutY(LABEL_LAYOUT_Y);
        this.getPane().getChildren().add(_label);

        _bar.setPrefSize(PREF_WIDTH, PREF_HEIGHT);
        _bar.setLayoutX(LAYOUT_X);
        _bar.setLayoutY(BAR_LAYOUT_Y);
        _bar.setProgress(0);
        this.getPane().getChildren().add(_bar);

        setLayoutX(x);
        setLayoutY(y);
    }

    public DefaultSubScene(String name,double x, double y, int min_value, int max_value) {
        this(name, x,y);
        _slider.setMin(min_value);
        _slider.setMax(max_value);
        _slider.setValue(max_value);

        _slider.setLayoutX(LAYOUT_X);
        _slider.setLayoutY(LAYOUT_X);

        this.getPane().getChildren().add(_slider);

    }

    public void addSliderListener(ChangeListener<Number> changeListener) {
        _slider.valueProperty().addListener(changeListener);
    }

    public void setProgress(double val) {
        _bar.setProgress(val);
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}

