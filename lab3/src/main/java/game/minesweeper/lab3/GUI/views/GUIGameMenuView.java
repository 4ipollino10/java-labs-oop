package game.minesweeper.lab3.GUI.views;


import game.minesweeper.lab3.GUI.utils.MyButton;
import game.minesweeper.lab3.controllers.GameController;
import game.minesweeper.lab3.models.GameModel;
import game.minesweeper.lab3.utils.Cell;
import game.minesweeper.lab3.utils.Constants;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GUIGameMenuView extends GUIView{
    private final AnchorPane _anchorPane;
    private final List<MyButton> _buttons;
    private Cell[][] _cells;
    private final GameModel _model;
    private final GameController _controller;


    public GUIGameMenuView(Stage stage, int matrixSize){
        super(stage);
        _model = new GameModel();
        _controller = new GameController(_model, matrixSize);
        _anchorPane = new AnchorPane();
        _buttons = new ArrayList<>();
        _cells = new Cell[matrixSize][matrixSize];
        for(int i = 0;i < matrixSize; ++i){
            for(int j = 0; j < matrixSize; ++j){
                _cells[i][j] = new Cell(Constants.CLOSED);
            }
        }
        createButtons();
        createBg();
        createCells();
        setNewScene(_anchorPane);

        show();
    }

    private void createButtons(){

        MyButton backButton = new MyButton(Constants.BACK_BUTTON_NAME);

        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                new GUIModeMenuView(getStage());
            }
        });
        addButtons(backButton);
    }

    private void createCells(){
        _cells = _model.getPlayerMatrix();

        for(int i = 0; i < _model.getMatrixSize(); ++i){
            for(int j = 0; j < _model.getMatrixSize(); ++j){
                _cells[i][j].setCords(i, j);
                _anchorPane.getChildren().add(_cells[i][j]);
                int finalI = i;
                int finalJ = j;
                _cells[i][j].setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton().name().equals(MouseButton.PRIMARY.name())) {
                            _cells[finalI][finalJ].setTurnType(Constants.PRESS_TURN_TYPE);

                        }else if (event.getButton().name().equals(MouseButton.SECONDARY.name())) {

                            if (_cells[finalI][finalJ].getStyle().equals(Constants.BUTTON_FLAG_STYLE)) {
                                _cells[finalI][finalJ].setTurnType(Constants.UNFLAG_TURN_TYPE);

                            } else {

                                _cells[finalI][finalJ].setTurnType(Constants.FLAG_TURN_TYPE);

                            }
                        }else if (event.getButton().name().equals(MouseButton.MIDDLE.name())){
                            _cells[finalI][finalJ].setTurnType(Constants.REVEAL_TURN_TYPE);
                        }
                        _controller.checkSisTurn((finalI + 1) + " " + (finalJ + 1) + " " + _cells[finalI][finalJ].getTurnType() + " ");
                        String turnResult = _controller.setPlayerTurn();

                        if(Objects.equals(turnResult, Constants.GAME_OVER_LOOSE_MESSAGE)){
                            _anchorPane.getChildren().clear();
                            new GUIEndGameMenuView(getStage(), Constants.GAME_OVER_LOOSE_MESSAGE);

                        }else if(Objects.equals(turnResult, Constants.GAME_OVER_WIN_MESSAGE)){
                            _anchorPane.getChildren().clear();
                            new GUIEndGameMenuView(getStage(), Constants.GAME_OVER_WIN_MESSAGE);
                        }
                    }
                });

            }
        }
    }

    private void addButtons(MyButton button){
        button.setLayoutX(Constants.MENU_BUTTON_START_X1);
        button.setLayoutY(Constants.MENU_BUTTON_START_Y2 + _buttons.size() * Constants.SHIFT);
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

