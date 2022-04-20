package game.minesweeper.lab3.SIS.views;

import game.minesweeper.lab3.models.StatisticModel;
import game.minesweeper.lab3.utils.Constants;
import game.minesweeper.lab3.SIS.utils.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

public class SISStatisticMenuView implements SISView {

    private final HashMap<String, Command> _commands;

    private final StatisticModel _model;

    public SISStatisticMenuView(){

        _model = new StatisticModel();
        _commands = new HashMap<>();
        createNewCommands();

    }

    private void addMenuCommands(Command command){
        _commands.put(Constants.BACK_COMMAND_NAME, command);
    }

    private void createNewCommands(){
        Command backCommand = new Command(SISMainMenuView::new);

        addMenuCommands(backCommand);

    }
    @Override
    public SISView show() throws IOException {
        List<String[]> data = _model.getStat();
        int i = 0;
        for (String[] d : data) {
            if (i == Constants.MAX_TOP_PLAYERS_COUNT) {
                break;
            }
            System.out.println(i++ + 1 + Constants.BRACKET + d[Constants.NAME] + Constants.SPACE + d[Constants.COUNT]);
        }
        System.out.println(Constants.BACK_COMMAND_NAME);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = reader.readLine();
        if(_commands.containsKey(command)){
            return _commands.get(command).action();
        }else{
            System.out.println(Constants.WRONG_COMMAND_ERROR_TEXT);
        }
        return this;
    }

}

