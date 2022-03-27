package game.minesweeper.lab3.GUI.views;



import game.minesweeper.lab3.GUI.utils.MyButton;

import game.minesweeper.lab3.utils.Constants;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GUIMainMenuView extends GUIView{
    private final AnchorPane _anchorPane;
    private final List<MyButton> _buttons;

    public GUIMainMenuView(Stage stage){
        super(stage);

        _buttons = new ArrayList<>();
        _anchorPane = new AnchorPane();
        createButtons();
        createBg();
        setNewScene(_anchorPane);
        show();
    }


    private void createButtons(){
        MyButton playButton = new MyButton(Constants.PlAY_BUTTON_NAME);
        MyButton statisticButton = new MyButton(Constants.STATISTIC_BUTTON_NAME);
        MyButton exitButton = new MyButton(Constants.EXIT_BUTTON_NAME);

        playButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                _anchorPane.getChildren().clear();
                new GUIModeMenuView(getStage());
            }
        });

        statisticButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                _anchorPane.getChildren().clear();
                new GUIStatisticMenuView(getStage());
            }
        });

        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                _anchorPane.getChildren().clear();
                System.exit(0);
            }
        });
        addButtons(playButton);
        addButtons(statisticButton);
        addButtons(exitButton);
    }

    private void addButtons(MyButton button){
        button.setLayoutX(Constants.MENU_BUTTON_START_X);
        button.setLayoutY(Constants.MENU_BUTTON_START_Y + _buttons.size() * Constants.SHIFT);
        _buttons.add(button);
        _anchorPane.getChildren().add(button);
    }

    private void createBg() {
        Image backgroundImage = new Image(Constants.BG_IMAGE, 0, 0, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        _anchorPane.setBackground(new Background(background));
    }

}
