package Commands;

import Contexts.Context;
import Constants.Constants;
import Exceptions.BadNumOfArgsException;
import Exceptions.EmptyStackSectionException;

import java.util.EmptyStackException;
import java.util.List;


public class PrintCommand extends Command {
    public PrintCommand(List<String> args) throws BadNumOfArgsException {
        super(args);
        if(args.size() != Constants.MAX_DEFAULT_COMMAND_ARGS_AMOUNT){
            throw new BadNumOfArgsException(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_EXCEPTION_ERROR_TEXT);
        }
    }

    @Override
    public String execute(Context context) throws EmptyStackSectionException {
        try{
            return context.getStackPeek().toString();
        }catch (EmptyStackException e){
            throw new EmptyStackSectionException(Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT);
        }
    }
}
