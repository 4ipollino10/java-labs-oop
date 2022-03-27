package ru.gulyaev.Commands;


import Contexts.Context;
import Exceptions.*;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class Command {
    private List<String> _args;

    public Command(List<String> args) throws BadNumOfArgsException{
        _args = args;
    }

    public List<String> getArgs(){
        return _args;
    }

    public abstract String execute(Context context) throws EmptyStackSectionException, DivisionByZeroException, MathException, EmptyVarException;

}