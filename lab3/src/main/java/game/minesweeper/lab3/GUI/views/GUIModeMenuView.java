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

public class GUIModeMenuView extends GUIView{
    private final AnchorPane _anchorPane;
    private final List<MyButton> _buttons;


    public GUIModeMenuView(Stage stage){
        super(stage);
        _anchorPane = new AnchorPane();
        _buttons = new ArrayList<>();
        createButtons();
        createBg();
        setNewScene(_anchorPane);
        show();
    }

    private void createButtons(){
        MyButton fstModeButton = new MyButton(Constants.FST_MODE_BUTTON_NAME);
        MyButton sndModeButton = new MyButton(Constants.SND_MODE_BUTTON_NAME);
        MyButton trdModeButton = new MyButton(Constants.TRD_MODE_BUTTON_NAME);
        MyButton backButton = new MyButton(Constants.BACK_BUTTON_NAME);

        setNextView(fstModeButton, Constants.FST_MODE_MATRIX_SIZE);
        setNextView(sndModeButton, Constants.SND_MODE_MATRIX_SIZE);
        setNextView(trdModeButton, Constants.TRD_MODE_MATRIX_SIZE);
        addButtons(backButton);
    }

    private void addButtons(MyButton button){
        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                new GUIMainMenuView(getStage());
            }
        });


        button.setLayoutX(Constants.MENU_BUTTON_START_X);
        button.setLayoutY(Constants.MENU_BUTTON_START_Y + _buttons.size() * Constants.SHIFT);
        _buttons.add(button);
        _anchorPane.getChildren().add(button);
    }

    private void setNextView(MyButton button, int mode){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                _anchorPane.getChildren().clear();
                new GUIGameMenuView(getStage(), mode);
            }
        });
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

