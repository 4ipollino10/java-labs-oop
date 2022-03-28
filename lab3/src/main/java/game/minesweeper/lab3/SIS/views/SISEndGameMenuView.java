package game.minesweeper.lab3.SIS.views;

import game.minesweeper.lab3.utils.Constants;
import game.minesweeper.lab3.controllers.StatisticController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SISEndGameMenuView implements SISView{

    private final StatisticController _controller;

    public SISEndGameMenuView(){
        _controller = new StatisticController();
    }

    @Override
    public SISView show() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Constants.END_GAME_MENU_MAP);

        String playerName = reader.readLine();
        System.out.println(checkName(playerName));
        _controller.setPlayerName(playerName);
        return new SISMainMenuView();
    }


    private String checkName(String playerName){
        if(playerName.length() > Constants.TOP_NAME_LENGTH | playerName.length() < Constants.BOTTOM_NAME_LENGTH){
            return Constants.BAD_NAME;
        }else{
            return Constants.GOOD_NAME;
        }
    }

}

