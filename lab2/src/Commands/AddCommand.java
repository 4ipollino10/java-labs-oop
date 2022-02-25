package Commands;

import Constants.Constants;
import Contexts.Context;
import Exceptions.BadNumOfArgsException;
import Exceptions.EmptyStackSectionException;

import java.util.EmptyStackException;
import java.util.List;

public class AddCommand extends Command{


    public AddCommand(List<String> args)throws BadNumOfArgsException {
        super(args);

        if(args.size() != Constants.MAX_DEFAULT_COMMAND_ARGS_AMOUNT){
            throw new BadNumOfArgsException(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_EXCEPTION_ERROR_TEXT);
        }
    }


    @Override
    public String execute(Context context) throws EmptyStackSectionException{
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
        context.addValue(firstVal + secondVal);
        return null;
    }
}
