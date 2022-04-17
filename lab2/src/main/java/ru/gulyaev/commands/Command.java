package ru.gulyaev.commands;
import ru.gulyaev.util.contexts.Context;
import ru.gulyaev.exceptions.*;

import java.util.List;

public abstract class Command {
    private final List<String> _args;

    public Command(List<String> args) throws BadNumOfArgsException{
        _args = args;
    }

    public List<String> getArgs(){
        return _args;
    }

    public abstract String execute(Context context) throws
            EmptyStackSectionException,
            DivisionByZeroException,
            MathException,
            EmptyVarException,
            MapException,
            BadVarNameException;

}