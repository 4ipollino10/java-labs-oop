package game.minesweeper.lab3.Controllers;

package game.MinesSweeper.controllers;

import game.MinesSweeper.models.GameModel;
import game.MinesSweeper.utils.Cell;
import game.MinesSweeper.utils.Constants;

import java.util.ArrayList;
import java.util.Objects;

public class GameController {

    private int _openedCells = 0;
    private String EXIT_MESSAGE = null;
    private final int _matrixSize;
    private final int _bombsAmount;
    ArrayList<String> _playerTurn;
    private final GameModel _model;
    private final Cell[][] _playerMatrix;
    private final Cell[][] _gameMatrix;


    public GameController(GameModel model, int mode){
        _model = model;
        if (Objects.equals(mode, Constants.FST_MODE_MATRIX_SIZE)) {
            _matrixSize = Constants.FST_MODE_MATRIX_SIZE;
            _bombsAmount = Constants.FST_MODE_BOMBS_AMOUNT;
        } else if (Objects.equals(mode, Constants.SND_MODE_MATRIX_SIZE)) {
            _matrixSize = Constants.SND_MODE_MATRIX_SIZE;
            _bombsAmount = Constants.SND_MODE_BOMBS_AMOUNT;
        } else {
            _matrixSize = Constants.TRD_MODE_MATRIX_SIZE;
            _bombsAmount = Constants.TRD_MODE_BOMBS_AMOUNT;
        }
        _model.setMatrixSize(_matrixSize);
        ArrayList<Integer> bombs = new ArrayList<>(_bombsAmount);
        _playerMatrix = new Cell[_matrixSize][_matrixSize];
        _gameMatrix = new Cell[_matrixSize][_matrixSize];
        for(int i = 0; i < _matrixSize; ++i){
            for(int j = 0; j < _matrixSize; ++j){
                _playerMatrix[i][j] = new Cell(Constants.CLOSED);
                _gameMatrix[i][j] = new Cell(Constants.EMPTY_CELL_TYPE);
            }
        }

        setBombs(bombs);
        setModelData();

    }

    public void freeModel(){
        _model.free();
    }

    public String checkSisTurn(String command){
        _playerTurn = new ArrayList<>();
        StringBuilder word = new StringBuilder(Constants.EMPTY_STR);
        for(int i = 0; i < command.length(); ++i){
            if(Character.isLetterOrDigit(command.charAt(i))){
                word.append(command.charAt(i));
            }
            else{
                if(word.length() > 0){
                    _playerTurn.add(word.toString());
                    word = new StringBuilder(Constants.EMPTY_STR);
                }
            }
        }
        if(_playerTurn.size() == Constants.MAX_TURN_ARGS_AMOUNT){
            if((!Objects.equals(_playerTurn.get(Constants.TRD_PLAYER_TURN_ARG), Constants.REVEAL_TURN_TYPE) &
                    !Objects.equals(_playerTurn.get(Constants.TRD_PLAYER_TURN_ARG), Constants.PRESS_TURN_TYPE) &
                    !Objects.equals(_playerTurn.get(Constants.TRD_PLAYER_TURN_ARG), Constants.FLAG_TURN_TYPE) &
                    !Objects.equals(_playerTurn.get(Constants.TRD_PLAYER_TURN_ARG), Constants.UNFLAG_TURN_TYPE)) |
                    Integer.parseInt(_playerTurn.get(Constants.FST_PLAYER_TURN_ARG)) < 0 |
                    Integer.parseInt(_playerTurn.get(Constants.FST_PLAYER_TURN_ARG)) > _matrixSize |
                    Integer.parseInt(_playerTurn.get(Constants.FST_PLAYER_TURN_ARG)) < 0 |
                    Integer.parseInt(_playerTurn.get(Constants.FST_PLAYER_TURN_ARG)) > _matrixSize)
            {
                return Constants.BAD_TURN;
            }

        }else {
            return Constants.BAD_TURN;
        }
        return Constants.TURN_COMMAND_NAME;
    }

    private void setModelData(){
        _model.setGameMatrix(_gameMatrix);
        _model.setPlayerMatrix(_playerMatrix);
    }

    public String updModel(){
        String turnResult = setPlayerTurn();
        setModelData();
        return turnResult;
    }

    public String setPlayerTurn(){
        int fstCord = Integer.parseInt(_playerTurn.get(Constants.FST_PLAYER_TURN_ARG)) - 1;
        int sndCord = Integer.parseInt(_playerTurn.get(Constants.SND_PLAYER_TURN_ARG)) - 1;
        if(Objects.equals(_playerTurn.get(Constants.TRD_PLAYER_TURN_ARG), Constants.FLAG_TURN_TYPE)){

            if(Objects.equals(_playerMatrix[fstCord][sndCord].getType(), Constants.CLOSED)){
                _playerMatrix[fstCord][sndCord].setType(Constants.FLAG);
                _playerMatrix[fstCord][sndCord].setButtonFlagType();

                setModelData();

                return Constants.GOOD_TURN;
            }else{
                return Constants.BAD_TURN;
            }
        }
        if(Objects.equals(_playerTurn.get(Constants.TRD_PLAYER_TURN_ARG), Constants.PRESS_TURN_TYPE)){
            for (Integer bomb : _model.getBombs()) {
                if (fstCord * _matrixSize + sndCord == bomb) {
                    _playerMatrix[fstCord][sndCord].setButtonBombType();
                    return Constants.GAME_OVER_LOOSE_MESSAGE;
                }
            }
            return UpdPlayerMatrix(fstCord, sndCord, _playerTurn.get(2));
        }
        if(Objects.equals(_playerTurn.get(Constants.TRD_PLAYER_TURN_ARG), Constants.REVEAL_TURN_TYPE)){
            return revealUpdPlayerMatrix(fstCord, sndCord);
        }
        if(Objects.equals(_playerTurn.get(Constants.TRD_PLAYER_TURN_ARG), Constants.UNFLAG_TURN_TYPE)){
            if(Objects.equals(_playerMatrix[fstCord][sndCord].getType(), Constants.FLAG)){
                _playerMatrix[fstCord][sndCord].setType(Constants.CLOSED);
                _playerMatrix[fstCord][sndCord].setButtonClosedType();
                setModelData();
                return Constants.GOOD_TURN;
            }else{
                return Constants.BAD_TURN;
            }
        }
        return Constants.BAD_TURN;
    }

    private void setBombs(ArrayList<Integer> bombs){
        ArrayList<Integer> tmp = new ArrayList<>();
        for(int i = 0; i < (_matrixSize * _matrixSize); ++i){
            tmp.add(i);
        }
        for(int i = 0; i < _bombsAmount; ++i){
            int cord = (int)(Math.random() * (_matrixSize * _matrixSize - i));

            _gameMatrix[tmp.get(cord)/_matrixSize][tmp.get(cord)%_matrixSize].setType(Constants.BOMB);
            _gameMatrix[tmp.get(cord)/_matrixSize][tmp.get(cord)%_matrixSize].setButtonBombType();

            bombs.add(tmp.get(cord));

            setCounters(tmp.get(cord) / _matrixSize, tmp.get(cord)%_matrixSize);
            tmp.remove(cord);

        }
        _model.setBombs(bombs);
    }

    private void setCounters(int x, int y){
        if(x > 0 & x < (_matrixSize - 1)){
            if(y > 0 & y < (_matrixSize - 1)){
                if(!Objects.equals(_gameMatrix[x - 1][y].getType(), Constants.BOMB)){
                    _gameMatrix[x - 1][y].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x - 1][y - 1].getType(), Constants.BOMB)){
                    _gameMatrix[x - 1][y - 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x - 1][y + 1].getType(), Constants.BOMB)){
                    _gameMatrix[x - 1][y + 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y - 1].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y - 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y + 1].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y + 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x][y - 1].getType(), Constants.BOMB)){
                    _gameMatrix[x][y - 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x][y + 1].getType(), Constants.BOMB)){
                    _gameMatrix[x][y + 1].riseCounter();
                }
            } else if(y == 0){
                if(!Objects.equals(_gameMatrix[x - 1][y].getType(), Constants.BOMB)){
                    _gameMatrix[x - 1][y].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x - 1][y + 1].getType(), Constants.BOMB)){
                    _gameMatrix[x - 1][y + 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x][y + 1].getType(), Constants.BOMB)){
                    _gameMatrix[x][y + 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y + 1].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y + 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y].riseCounter();
                }
            } else{
                if(!Objects.equals(_gameMatrix[x - 1][y].getType(), Constants.BOMB)){
                    _gameMatrix[x - 1][y].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x - 1][y - 1].getType(), Constants.BOMB)){
                    _gameMatrix[x - 1][y - 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x][y - 1].getType(), Constants.BOMB)){
                    _gameMatrix[x][y - 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y - 1].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y - 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y].riseCounter();
                }
            }
        } else if(x == 0){
            if(y > 0 & y < _matrixSize - 1){
                if(!Objects.equals(_gameMatrix[x + 1][y].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x][y + 1].getType(), Constants.BOMB)){
                    _gameMatrix[x][y + 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y + 1].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y + 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x][y - 1].getType(), Constants.BOMB)){
                    _gameMatrix[x][y - 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y - 1].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y - 1].riseCounter();
                }



            } else if(y == 0){
                if(!Objects.equals(_gameMatrix[x + 1][y].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x][y + 1].getType(), Constants.BOMB)){
                    _gameMatrix[x][y + 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y + 1].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y + 1].riseCounter();
                }

            } else{
                if(!Objects.equals(_gameMatrix[x][y - 1].getType(), Constants.BOMB)){
                    _gameMatrix[x][y - 1].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y].riseCounter();
                }

                if(!Objects.equals(_gameMatrix[x + 1][y - 1].getType(), Constants.BOMB)){
                    _gameMatrix[x + 1][y - 1].riseCounter();
                }

            }
        } else {
            if (y > 0 & y < _matrixSize - 1) {
                if (!Objects.equals(_gameMatrix[x - 1][y].getType(), Constants.BOMB)) {
                    _gameMatrix[x - 1][y].riseCounter();
                }

                if (!Objects.equals(_gameMatrix[x][y + 1].getType(), Constants.BOMB)) {
                    _gameMatrix[x][y + 1].riseCounter();
                }

                if (!Objects.equals(_gameMatrix[x - 1][y + 1].getType(), Constants.BOMB)) {
                    _gameMatrix[x - 1][y + 1].riseCounter();
                }

                if (!Objects.equals(_gameMatrix[x][y - 1].getType(), Constants.BOMB)) {
                    _gameMatrix[x][y - 1].riseCounter();
                }

                if (!Objects.equals(_gameMatrix[x - 1][y - 1].getType(), Constants.BOMB)) {
                    _gameMatrix[x - 1][y - 1].riseCounter();
                }


            } else if (y == 0) {
                if (!Objects.equals(_gameMatrix[x - 1][y].getType(), Constants.BOMB)) {
                    _gameMatrix[x - 1][y].riseCounter();
                }

                if (!Objects.equals(_gameMatrix[x][y + 1].getType(), Constants.BOMB)) {
                    _gameMatrix[x][y + 1].riseCounter();
                }

                if (!Objects.equals(_gameMatrix[x - 1][y + 1].getType(), Constants.BOMB)) {
                    _gameMatrix[x - 1][y + 1].riseCounter();
                }

            } else {
                if (!Objects.equals(_gameMatrix[x][y - 1].getType(), Constants.BOMB)) {
                    _gameMatrix[x][y - 1].riseCounter();
                }

                if (!Objects.equals(_gameMatrix[x - 1][y].getType(), Constants.BOMB)) {
                    _gameMatrix[x - 1][y].riseCounter();
                }

                if (!Objects.equals(_gameMatrix[x - 1][y - 1].getType(), Constants.BOMB)) {
                    _gameMatrix[x - 1][y - 1].riseCounter();
                }
            }
        }
        setModelData();
    }
    private Boolean isFlagNear(int x, int y) {
        int flagCounter = 0;
        if(!Objects.equals(_playerMatrix[x][y].getType(), Constants.CLOSED) && !Objects.equals(_playerMatrix[x][y].getType(), Constants.FLAG) && _playerMatrix[x][y].getCounter() > 0){
            if (x > 0 & x < _matrixSize - 1) {
                if (y > 0 & y < _matrixSize - 1) {
                    if(Objects.equals(_playerMatrix[x - 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x - 1][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x - 1][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                } else if (y == 0) {
                    if(Objects.equals(_playerMatrix[x - 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x - 1][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                } else {
                    if(Objects.equals(_playerMatrix[x - 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x - 1][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                }
            } else if (x == 0) {
                if (y > 0 & y < _matrixSize - 1) {
                    if(Objects.equals(_playerMatrix[x][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                } else if (y == 0) {
                    if(Objects.equals(_playerMatrix[x][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                } else {
                    if(Objects.equals(_playerMatrix[x][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x + 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                }
            } else {
                if (y > 0 & y < _matrixSize - 1) {
                    if(Objects.equals(_playerMatrix[x - 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x - 1][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x - 1][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                } else if (y == 0) {
                    if(Objects.equals(_playerMatrix[x - 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x - 1][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x][y + 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                } else {
                    if(Objects.equals(_playerMatrix[x - 1][y].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x - 1][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                    if(Objects.equals(_playerMatrix[x][y - 1].getType(), Constants.FLAG)){
                        flagCounter++;
                    }
                }
            }
        }else{
            return false;
        }
        return _playerMatrix[x][y].getCounter() == flagCounter;
    }


    private String revealUpdPlayerMatrix(int x, int y){
        if(isFlagNear(x, y)){
            _playerMatrix[x][y].setType(Constants.CLOSED);
            _openedCells--;
            _playerMatrix[x][y].setButtonClosedType();

            return UpdPlayerMatrix(x, y, _playerTurn.get(Constants.TRD_PLAYER_TURN_ARG));
        }else{
            return Constants.NO_FLAG_MESSAGE;
        }
    }



    private String UpdPlayerMatrix(int x, int y, String mode){

        if(Objects.equals(_playerMatrix[x][y].getType(), Constants.CLOSED) &&
                !Objects.equals(_playerMatrix[x][y].getType(), Constants.FLAG))
        {
            if(Objects.equals(_gameMatrix[x][y].getType(), Constants.BOMB)){
                EXIT_MESSAGE = Constants.GAME_OVER_LOOSE_MESSAGE;
                return EXIT_MESSAGE;
            }

            _playerMatrix[x][y].setType(_gameMatrix[x][y].getType());
            _playerMatrix[x][y].setButtonEmptyStyle();
            _openedCells++;
            if (Integer.parseInt(_gameMatrix[x][y].getType()) > 0 & Objects.equals(mode, Constants.PRESS_TURN_TYPE)) {
                setModelData();
                if(_openedCells == _matrixSize * _matrixSize - _bombsAmount) {
                    return Constants.GAME_OVER_WIN_MESSAGE;
                }
                return Constants.GOOD_TURN;
            } else {
                if (x > 0 & x < _matrixSize - 1) {
                    if (y > 0 & y < _matrixSize - 1) {
                        UpdPlayerMatrix(x - 1, y, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x - 1, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y - 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x, y - 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x - 1, y - 1, Constants.PRESS_TURN_TYPE);
                    } else if (y == 0) {
                        UpdPlayerMatrix(x - 1, y, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x - 1, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y, Constants.PRESS_TURN_TYPE);
                    } else {
                        UpdPlayerMatrix(x - 1, y, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x - 1, y - 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x, y - 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y - 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y, Constants.PRESS_TURN_TYPE);
                    }
                } else if (x == 0) {
                    if (y > 0 & y < _matrixSize - 1) {
                        UpdPlayerMatrix(x, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y - 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y - 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x, y - 1, Constants.PRESS_TURN_TYPE);
                    } else if (y == 0) {
                        UpdPlayerMatrix(x, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y, Constants.PRESS_TURN_TYPE);
                    } else {
                        UpdPlayerMatrix(x + 1, y, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x + 1, y - 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x, y - 1, Constants.PRESS_TURN_TYPE);
                    }
                } else {
                    if (y > 0 & y < _matrixSize - 1) {
                        UpdPlayerMatrix(x - 1, y, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x - 1, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x, y - 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x - 1, y - 1, Constants.PRESS_TURN_TYPE);
                    } else if (y == 0) {
                        UpdPlayerMatrix(x - 1, y, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x - 1, y + 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x, y + 1, Constants.PRESS_TURN_TYPE);
                    } else {
                        UpdPlayerMatrix(x - 1, y, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x, y - 1, Constants.PRESS_TURN_TYPE);
                        UpdPlayerMatrix(x - 1, y - 1, Constants.PRESS_TURN_TYPE);
                    }
                }
            }
        }
        setModelData();
        if(Objects.equals(EXIT_MESSAGE, Constants.GAME_OVER_LOOSE_MESSAGE)){
            return EXIT_MESSAGE;
        }
        if(_openedCells == _matrixSize * _matrixSize - _bombsAmount) {
            return Constants.GAME_OVER_WIN_MESSAGE;
        }
        return Constants.GOOD_TURN;
    }
}

