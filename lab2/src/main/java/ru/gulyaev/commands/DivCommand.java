package ru.gulyaev.commands;


import java.util.EmptyStackException;
import java.util.List;
import ru.gulyaev.util.Constants;
import ru.gulyaev.util.contexts.Context;
import ru.gulyaev.exceptions.BadNumOfArgsException;
import ru.gulyaev.exceptions.DivisionByZeroException;
import ru.gulyaev.exceptions.EmptyStackSectionException;

public class DivCommand extends Command {
    public DivCommand(List<String> args) throws BadNumOfArgsException {
        super(args);
        if(args.size() != Constants.MAX_DEFAULT_COMMAND_ARGS_AMOUNT){
            throw new BadNumOfArgsException(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_EXCEPTION_ERROR_TEXT);
        }
    }

    @Override
    public String execute(Context context) throws
            EmptyStackSectionException,
            DivisionByZeroException {

        double firstVal, secondVal;
        try {
            firstVal = context.pop();
        }catch (EmptyStackException e){
            throw new EmptyStackSectionException(Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT);
        }
        try {
            secondVal = context.pop();
        }catch (EmptyStackException e){
            context.push(firstVal);
            throw new EmptyStackSectionException(Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT);
        }
        if(secondVal == 0){
            context.push(secondVal);
            context.push(firstVal);
            throw new DivisionByZeroException(Constants.DIVISION_BY_ZERO_EXCEPTION_ERROR_TEXT);
        }
        context.addValue(firstVal/secondVal);
        return null;
    }
}
