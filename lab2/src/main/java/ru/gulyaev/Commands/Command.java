package ru.gulyaev.Commands;
import ru.gulyaev.Contexts.Context;
import ru.gulyaev.Exceptions.*;

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