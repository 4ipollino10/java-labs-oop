package game.minesweeper.lab3.SIS.views;

import game.minesweeper.lab3.utils.Constants;
import game.minesweeper.lab3.SIS.utils.Command;
import game.minesweeper.lab3.SIS.utils.MyEventHandler;


import java.io.*;
import java.util.HashMap;

public class SISMainMenuView implements SISView {

    private final HashMap<String, Command> _commands;

    public SISMainMenuView() {
        _commands = new HashMap<>();
        createNewCommands();
    }

    private void addMenuCommands(Command command, String name){
        _commands.put(name, command);
    }

    private void createNewCommands(){

        Command playCommand = new Command(new MyEventHandler() {
            public SISView handle(){
                return new SISModeMenuView();
            }
        });

        Command statisticCommand = new Command(new MyEventHandler() {
            public SISView handle(){
                return new SISStatisticMenuView();
            }
        });

        Command exitCommand = new Command(new MyEventHandler() {
            public SISView handle(){
                return null;
            }
        });
        addMenuCommands(playCommand, Constants.PLAY_COMMAND_NAME);
        addMenuCommands(statisticCommand, Constants.STATISTIC_COMMAND_NAME);
        addMenuCommands(exitCommand, Constants.EXIT_COMMAND_NAME);
    }

    @Override
    public SISView show() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Constants.MAIN_MENU_MAP);
        String command = reader.readLine();

        if(_commands.containsKey(command)){
            return _commands.get(command).action();
        }else{
            System.out.println(Constants.WRONG_COMMAND_ERROR_TEXT);
        }
        return this;
    }

}

