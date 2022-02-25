package Factorys;

import Commands.Command;
import Constants.Constants;
import Exceptions.CommandNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class CommandFactory {
    private static CommandFactory _commandFactory;
    private final Properties _commandProperties;

    public CommandFactory()throws IOException{
        _commandProperties = new Properties();
        _commandProperties.load(CommandFactory
                .class
                .getClassLoader()
                .getResourceAsStream(Constants.COMMANDS_PATH));
    }

    public static CommandFactory getInstance()throws IOException{
        if(_commandFactory == null){
            _commandFactory = new CommandFactory();
        }
        return _commandFactory;
    }

    public Command getCommand(String command, List<String> args) throws
            IOException,
            CommandNotFoundException,
            ClassNotFoundException,
            NoSuchMethodException,
            IllegalAccessException,
            InstantiationException,
            InvocationTargetException {

        Properties config = new Properties();
        config.load(CommandFactory.class.getClassLoader().getResourceAsStream(Constants.CONFIG_PATH));

        if(!_commandProperties.containsKey(command)){
            throw new CommandNotFoundException(Constants.COMMAND_NOT_FOUND_EXCEPTION_ERROR_TEXT + command);
        }
        return (Command)Class.
                forName(config.getProperty(Constants.COMMAND_CLASS_PATH) +
                _commandProperties.get(command))
                .getConstructor(new Class[]{List.class})
                .newInstance(args);
    }
}
