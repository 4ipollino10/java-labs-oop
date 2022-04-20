package game.minesweeper.lab3.GUI.views;



import game.minesweeper.lab3.GUI.utils.MyButton;
import game.minesweeper.lab3.controllers.StatisticController;
import game.minesweeper.lab3.models.StatisticModel;
import game.minesweeper.lab3.utils.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GUIStatisticMenuView extends GUIView{
    private final AnchorPane _anchorPane;
    private final List<MyButton> _buttons;
    private final StatisticModel _model;
    private StatisticController _controller;

    public GUIStatisticMenuView(Stage stage){
        super(stage);
        _buttons = new ArrayList<>();
        _anchorPane = new AnchorPane();
        _model = new StatisticModel();
        _controller = new StatisticController();

        createButtons();
        createBg();
        setStatistic();
        setNewScene(_anchorPane);
        show();
    }
    private void createButtons(){
        MyButton backButton = new MyButton(Constants.BACK_BUTTON_NAME);


        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                _anchorPane.getChildren().clear();
                new GUIMainMenuView(getStage());
            }
        });

        addButtons(backButton);
    }

    private void setStatistic() {
        List<String[]> data = _model.getStat();
        int i = 0;
        Label topLabel = new Label(Constants.STAT_LABEL);
        topLabel.setFont(Constants.getSmallFont());
        topLabel.setStyle(Constants.LABEL_STYLE);
        topLabel.setLayoutX(Constants.STAT_TOP_LAYOUT_POS_X);
        topLabel.setLayoutY(Constants.STAT_TOP_LAYOUT_POS_Y);
        _anchorPane.getChildren().add(topLabel);
        for (String[] d : data) {
            if (i == Constants.MAX_TOP_PLAYERS_COUNT) break;
            Label label = new Label(i + 1 + Constants.BRACKET + d[Constants.NAME] + Constants.SPACE + d[Constants.COUNT]);
            label.setFont(Constants.getSmallFont());
            label.setStyle(Constants.LABEL_STYLE);
            label.setLayoutX(Constants.STAT_TOP_LAYOUT_POS_X);
            label.setLayoutY(Constants.STAT_Y_LAYOUT_POS + i++ * Constants.SMALL_SHIFT);
            _anchorPane.getChildren().add(label);
        }
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
