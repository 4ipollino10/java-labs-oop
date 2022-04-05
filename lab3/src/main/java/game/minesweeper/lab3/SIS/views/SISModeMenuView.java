package game.minesweeper.lab3.SIS.views;

import game.minesweeper.lab3.utils.Constants;
import game.minesweeper.lab3.SIS.utils.Command;
import game.minesweeper.lab3.SIS.utils.MyEventHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class SISModeMenuView implements SISView {

    private final HashMap<String, Command> _commands;

    public SISModeMenuView(){
        _commands = new HashMap<>();
        createNewCommands();

    }

    private void addMenuCommands(Command command, String name){
        _commands.put(name, command);
    }

    private void createNewCommands(){
        Command fstModeCommand = new Command(new MyEventHandler() {

            public SISView handle() {
                return new SISGameMenuView(Constants.FST_MODE_MATRIX_SIZE);
            }
        });
        Command sndModeCommand = new Command(new MyEventHandler() {

            public SISView handle() {
                return new SISGameMenuView(Constants.SND_MODE_MATRIX_SIZE);
            }
        });
        Command trdModeCommand = new Command(new MyEventHandler() {

            public SISView handle() {
                return new SISGameMenuView(Constants.TRD_MODE_MATRIX_SIZE);
            }
        });
        Command backCommand = new Command(new MyEventHandler() {

            public SISView handle() {
                return new SISMainMenuView();
            }
        });

        addMenuCommands(fstModeCommand, Constants.FST_MODE_COMMAND_NAME);
        addMenuCommands(sndModeCommand, Constants.SND_MODE_COMMAND_NAME);
        addMenuCommands(trdModeCommand, Constants.TRD_MODE_COMMAND_NAME);
        addMenuCommands(backCommand, Constants.BACK_COMMAND_NAME);

    }
    @Override
    public SISView show() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(Constants.MODE_MENU_MAP);
        String command = reader.readLine();

        if(_commands.containsKey(command)){
            return _commands.get(command).action();
        }else{
            System.out.println(Constants.WRONG_COMMAND_ERROR_TEXT);
        }
        return this;
    }

}

