package game.minesweeper.lab3.GUI.views;


import game.minesweeper.lab3.GUI.utils.MyButton;
import game.minesweeper.lab3.controllers.StatisticController;
import game.minesweeper.lab3.utils.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GUIEndGameMenuView extends GUIView{
    private final AnchorPane _anchorPane;
    private final List<MyButton> _buttons;
    private final Label _label;
    private final StatisticController _controller;



    public GUIEndGameMenuView(Stage stage, String gameResult){
        super(stage);
        _controller = new StatisticController();
        _label = new Label();
        _anchorPane = new AnchorPane();
        _buttons = new ArrayList<>();
        createTextField();
        createBg(gameResult);
        setNewScene(_anchorPane);
        show();
    }

    private void createTextField(){
        final TextField name = new TextField();
        name.setPrefWidth(Constants.PREF_WIDTH);
        name.setPrefHeight(Constants.PREF_HEIGHT);
        name.setFont(Constants.getSmallFont());
        name.setPromptText(Constants.PROMPT_TEXT);
        name.setLayoutX(Constants.TEXT_FIELD_START_X);
        name.setLayoutY(Constants.TEXT_FIELD_START_Y);
        _anchorPane.getChildren().add(name);
        createButtons(name);


    }


    private void createButtons(TextField name){

        MyButton enterButton = new MyButton(Constants.ENTER_BUTTON_NAME);


        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!name.getText().isEmpty()) {
                    if (_controller.setPlayerName(name.getText())) {

                        new GUIMainMenuView(getStage());
                    }
                    else {
                        try {
                            _anchorPane.getChildren().remove(Constants.LABEL_PANE);
                        }catch (IndexOutOfBoundsException ignored){}
                        _label.setText(Constants.BAD_NAME);
                        _label.setStyle(Constants.LABEL_STYLE);
                        _label.setLayoutX(Constants.LABEL_START_X);
                        _label.setLayoutY(Constants.LABEL_START_Y);
                        _anchorPane.getChildren().add(_label);
                    }
                } else {
                    try {
                        _anchorPane.getChildren().remove(Constants.LABEL_PANE);
                    }catch (IndexOutOfBoundsException ignored){}
                    _label.setText(Constants.NAME_IS_EMPTY);
                    _label.setStyle(Constants.LABEL_STYLE);
                    _label.setLayoutX(Constants.LABEL_START_X);
                    _label.setLayoutY(Constants.LABEL_START_Y);
                    _anchorPane.getChildren().add(_label);
                }

            }


        });
        addButtons(enterButton);
    }

    private void addButtons(MyButton button){
        button.setLayoutX(Constants.MENU_BUTTON_START_X);
        button.setLayoutY(Constants.MENU_BUTTON_START_Y + _buttons.size() * Constants.SHIFT);
        _buttons.add(button);
        _anchorPane.getChildren().add(button);
    }

    private void createBg(String gameResult) {
        if(Objects.equals(gameResult, Constants.GAME_OVER_WIN_MESSAGE)){
            Image backgroundImage = new Image(Constants.BG_WIN_IMAGE, 0, 0, false, false);
            BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                    BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
            _anchorPane.setBackground(new Background(background));
        }else{
            Image backgroundImage = new Image(Constants.BG_LOST_IMAGE, 0, 0, false, false);
            BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                    BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
            _anchorPane.setBackground(new Background(background));
        }
    }

}
