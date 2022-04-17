package ru.gulyaev.factorys;

import ru.gulyaev.commands.Command;
import ru.gulyaev.util.Constants;
import ru.gulyaev.exceptions.CommandNotFoundException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

public class Factory {
    private static Factory _commandFactory;
    private final Properties _commandProperties;

    public Factory()throws IOException{
        _commandProperties = new Properties();
        _commandProperties.load(Factory.class.getClassLoader().getResourceAsStream(Constants.COMMANDS_PATH));
    }

    public static Factory getInstance()throws IOException{
        if(_commandFactory == null){
            _commandFactory = new Factory();
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
        config.load(Factory.class.getClassLoader().getResourceAsStream(Constants.CONFIG_PATH));

        if(!_commandProperties.containsKey(command)){
            throw new CommandNotFoundException(Constants.COMMAND_NOT_FOUND_EXCEPTION_ERROR_TEXT + command);
        }
        return (Command)Class.
                forName(config.getProperty(Constants.COMMAND_CLASS_PATH) + _commandProperties.get(command)).getConstructor(new Class[]{List.class}).newInstance(args);
    }


}
