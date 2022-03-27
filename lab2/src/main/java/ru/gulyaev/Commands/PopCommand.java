package ru.gulyaev.Commands;

import Contexts.Context;
import Constants.Constants;
import Exceptions.BadNumOfArgsException;
import Exceptions.EmptyStackSectionException;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;
import java.util.Stack;


public class PopCommand extends Command{
    public PopCommand(List<String> args) throws BadNumOfArgsException {
        super(args);
        if(args.size() != Constants.MAX_DEFAULT_COMMAND_ARGS_AMOUNT){
            throw new BadNumOfArgsException(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_ERROR_TEXT);
        }
    }

    @Override
    public String execute(Context context) throws EmptyStackSectionException {
        try{
            context.pop();
        }catch (EmptyStackException e){
            throw new EmptyStackSectionException(Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT);
        }
        return null;
    }
}