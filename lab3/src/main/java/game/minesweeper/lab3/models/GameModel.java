package game.minesweeper.lab3.models;

import game.minesweeper.lab3.utils.Cell;

import java.util.ArrayList;

//10 50 100

public class GameModel {

    private int _matrixSize;
    private Cell[][] _playerMatrix;
    private Cell[][] _gameMatrix;
    private ArrayList<Integer> _bombs;

    public GameModel() {}

    public void setPlayerMatrix(Cell[][] playerMatrix){
        _playerMatrix = playerMatrix;
    }

    public void setGameMatrix(Cell[][] gameMatrix){
        _gameMatrix = gameMatrix;
    }

    public Cell[][] getGameMatrix(){
        return _gameMatrix;
    }

    public Cell[][] getPlayerMatrix(){
        return _playerMatrix;
    }

    public void setBombs(ArrayList<Integer> bombs){
        _bombs = bombs;
    }

    public ArrayList<Integer> getBombs(){
        return _bombs;
    }

    public void setMatrixSize(int matrixSize){
        _matrixSize = matrixSize;
    }

    public int getMatrixSize(){
        return _matrixSize;
    }

    public void free(){
        _gameMatrix = null;
        _playerMatrix = null;
    }


}
