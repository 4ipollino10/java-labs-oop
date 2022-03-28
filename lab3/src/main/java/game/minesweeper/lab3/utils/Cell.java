package game.minesweeper.lab3.utils;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class Cell extends Button {
    private static final String BUTTON_COUNTER_STYLE_1 = "-fx-background-color: transparent; -fx-background-image: url('11.png');";
    private static final String BUTTON_COUNTER_STYLE_2 = "-fx-background-color: transparent; -fx-background-image: url('22.png');";
    private static final String BUTTON_COUNTER_STYLE_3 = "-fx-background-color: transparent; -fx-background-image: url('33.png');";
    private static final String BUTTON_COUNTER_STYLE_4 = "-fx-background-color: transparent; -fx-background-image: url('44.png');";
    private static final String BUTTON_COUNTER_STYLE_5 = "-fx-background-color: transparent; -fx-background-image: url('55.png');";
    private static final String BUTTON_COUNTER_STYLE_6 = "-fx-background-color: transparent; -fx-background-image: url('66.png');";
    private static final String BUTTON_COUNTER_STYLE_7 = "-fx-background-color: transparent; -fx-background-image: url('77.png');";
    private static final String BUTTON_COUNTER_STYLE_8 = "-fx-background-color: transparent; -fx-background-image: url('88.png');";


    public ArrayList<String> _counters;
    public static final double WIDTH = 30;
    public static final double HEIGHT = 30;
    public static final double SHIFT = 1;
    private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('empty.png');";
    private static final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('lock.png');";

    private static final String BUTTON_BOMB_STYLE = "-fx-background-color: transparent; -fx-background-image: url('bomb.png');";
    private String _turnType;
    private String _cellType;
    public Cell(String type){
        setText(null);
        createCounters();
        _cellType = type;
        setPrefWidth(WIDTH);
        setPrefHeight(HEIGHT);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    public void setType(String type) {
        _cellType = type;
    }

    public String getType(){
        return _cellType;
    }

    public void setTurnType(String type){
        _turnType = type;
    }

    public String getTurnType(){
        return _turnType;
    }

    public int getCounter(){
        return Integer.parseInt(_cellType);
    }

    public void riseCounter(){
        _cellType = Integer.toString(Integer.parseInt(_cellType) + 1);
        setStyle(_counters.get(Integer.parseInt(_cellType) - 1));
    }

    public void setButtonFlagType(){
        setStyle(Constants.BUTTON_FLAG_STYLE);
    }

    public void setButtonBombType(){
        setStyle(BUTTON_BOMB_STYLE);
    }

    public void setButtonClosedType(){
        setStyle(BUTTON_FREE_STYLE);
    }

    public void setButtonEmptyStyle() {
        setStyle(_counters.get(Integer.parseInt(_cellType)));
    }

    public void setCords(int x, int y) {
        setLayoutY(Constants.MENU_BUTTON_START_Y + WIDTH * x);
        setLayoutX(Constants.MENU_BUTTON_START_Y + HEIGHT * y);
    }

    private void initializeButtonListeners() {



        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setLayoutY(getLayoutY() - SHIFT);
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                setLayoutY(getLayoutY() + SHIFT);
            }
        });


    }

    public void createCounters(){
        _counters = new ArrayList<>();
        _counters.add(BUTTON_PRESSED_STYLE);
        _counters.add(BUTTON_COUNTER_STYLE_1);
        _counters.add(BUTTON_COUNTER_STYLE_2);
        _counters.add(BUTTON_COUNTER_STYLE_3);
        _counters.add(BUTTON_COUNTER_STYLE_4);
        _counters.add(BUTTON_COUNTER_STYLE_5);
        _counters.add(BUTTON_COUNTER_STYLE_6);
        _counters.add(BUTTON_COUNTER_STYLE_7);
        _counters.add(BUTTON_COUNTER_STYLE_8);
    }



}

