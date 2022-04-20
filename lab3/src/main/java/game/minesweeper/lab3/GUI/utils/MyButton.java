package game.minesweeper.lab3.GUI.utils;


import game.minesweeper.lab3.utils.Constants;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MyButton extends Button {

    public static final double WIDTH = 200;
    public static final double HEIGHT = 50;
    public static final double PRESSED_HEIGHT = 47;
    public static final double SHIFT = 3;
    private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('button-pressed.png');";
    private static final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('button.png');";

    public MyButton(String text) {
        setText(text);
        setFont(Constants.getDefaultFont());
        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(PRESSED_HEIGHT);
        setLayoutY(getLayoutY() + SHIFT);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(PRESSED_HEIGHT);
        setLayoutY(getLayoutY() - SHIFT);
    }

    private void initializeButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    MyButton.this.setButtonPressedStyle();
                }

            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    MyButton.this.setButtonReleasedStyle();
                }

            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setLayoutY(getLayoutY() - SHIFT);
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setLayoutY(getLayoutY() + SHIFT);
            }
        });
    }
}
