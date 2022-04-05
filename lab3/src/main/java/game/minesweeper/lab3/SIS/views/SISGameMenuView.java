package game.minesweeper.lab3.SIS.views;

import game.minesweeper.lab3.utils.Constants;
import game.minesweeper.lab3.utils.Cell;
import game.minesweeper.lab3.SIS.utils.Command;
import game.minesweeper.lab3.SIS.utils.MyEventHandler;
import game.minesweeper.lab3.controllers.GameController;
import game.minesweeper.lab3.models.GameModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

public class SISGameMenuView implements SISView {
    public static final String DOUBLE_SPACE = "  ";
    private final HashMap<String, Command> _commands;
    private final GameModel _model;
    private final GameController _controller;

    public SISGameMenuView(int mode) {
        _commands = new HashMap<>();
        createNewCommands();
        _model = new GameModel();
        _controller = new GameController(_model, mode);
    }

    private void addMenuCommands(Command command){
        _commands.put(Constants.BACK_COMMAND_NAME, command);
    }

    private void createNewCommands(){
        Command backCommand = new Command(new MyEventHandler() {

            public SISView handle() {
                _controller.freeModel();
                return new SISModeMenuView();
            }
        });

        addMenuCommands(backCommand);
    }

    @Override
    public SISView show() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Cell[][] playerMatrix1 = _model.getGameMatrix();
        Cell[][] playerMatrix2 = _model.getPlayerMatrix();
        for(int i = 0; i < _model.getMatrixSize(); ++i){
            for(int j = 0; j < _model.getMatrixSize(); ++j){
                System.out.print(playerMatrix1[i][j].getType() + DOUBLE_SPACE);
            }
            System.out.println();
        }
        System.out.println();
        for(int i = 0; i < _model.getMatrixSize(); ++i){
            for(int j = 0; j < _model.getMatrixSize(); ++j){
                System.out.print(playerMatrix2[i][j].getType() + DOUBLE_SPACE);
            }
            System.out.println();
        }

        String command = reader.readLine();
        String turn = _controller.checkSisTurn(command + Constants.SPACE);
        if (_commands.containsKey(command)) {
            return _commands.get(command).action();
        }else if(!Objects.equals(turn, Constants.TURN_COMMAND_NAME)){
            System.out.println(turn);
        }else{
            String turnResult = _controller.updModel();
            return checkTurnResult(turnResult);
        }

        return this;
    }

    private SISView checkTurnResult(String turnResult){
        System.out.println(turnResult);

        if(Objects.equals(turnResult, Constants.GAME_OVER_LOOSE_MESSAGE)){
            return new SISMainMenuView();

        }else if(Objects.equals(turnResult, Constants.GAME_OVER_WIN_MESSAGE)){
            return new SISEndGameMenuView();

        }
        return this;
    }
}

