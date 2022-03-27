package ru.gulyaev.Commands;

import Constants.Constants;
import Exceptions.BadNumOfArgsException;
import Exceptions.DivisionByZeroException;
import Exceptions.EmptyStackSectionException;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import Contexts.Context;
public class DivCommand extends Command {
    public DivCommand(List<String> args) throws BadNumOfArgsException {
        super(args);
        if(args.size() != Constants.MAX_DEFAULT_COMMAND_ARGS_AMOUNT){
            throw new BadNumOfArgsException(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_ERROR_TEXT);
        }
    }

    @Override
    public String execute(Context context) throws EmptyStackSectionException, DivisionByZeroException {
        double firstVal, secondVal;
        try {
            firstVal = context.pop();
        }catch (EmptyStackException e){
            throw new EmptyStackSectionException(Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT);
        }
        try {
            secondVal = context.pop();
        }catch (EmptyStackException e){
            throw new EmptyStackSectionException(Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT);
        }
        if(secondVal == 0){
            throw new DivisionByZeroException(Constants.DIVISION_BY_ZERO_EXCEPTION_ERROR_TEXT);
        }
        context.addValue(firstVal/secondVal);
        return null;
    }
}
