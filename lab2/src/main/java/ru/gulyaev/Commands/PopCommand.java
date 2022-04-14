package ru.gulyaev.Commands;

import ru.gulyaev.Constants.Constants;
import ru.gulyaev.Contexts.Context;
import ru.gulyaev.Exceptions.BadNumOfArgsException;
import ru.gulyaev.Exceptions.EmptyStackSectionException;

import java.util.EmptyStackException;
import java.util.List;



public class PopCommand extends Command{
    public PopCommand(List<String> args) throws BadNumOfArgsException {
        super(args);
        if(args.size() != Constants.MAX_DEFAULT_COMMAND_ARGS_AMOUNT){
            throw new BadNumOfArgsException(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_EXCEPTION_ERROR_TEXT);
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