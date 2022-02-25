package Commands;

import Contexts.Context;
import Constants.Constants;
import Exceptions.BadNumOfArgsException;
import Exceptions.EmptyVarException;
import Exceptions.MapException;

import java.util.List;
import java.util.Scanner;

public class PushCommand extends Command{
    public PushCommand(List<String> args) throws BadNumOfArgsException {
        super(args);
        if(args.size() != Constants.MAX_PUSH_COMMAND_ARGS_AMOUNT){
            throw new BadNumOfArgsException(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_EXCEPTION_ERROR_TEXT);
        }
    }

    @Override
    public String execute(Context context) throws
            EmptyVarException,
            MapException {

        double val;
        if(new Scanner(getArgs().get(Constants.PUSH_VALUE_ARG)).hasNextDouble()){
            val = Double.parseDouble(getArgs().get(Constants.PUSH_VALUE_ARG));
            context.addValue(val);

        }else if(context.isHasValue(getArgs().get(Constants.PUSH_VALUE_ARG))){
            val = context.searchValue(getArgs().get(Constants.PUSH_VALUE_ARG));
            context.addValue(val);

        }else{
            throw new EmptyVarException(Constants.EMPTY_VAR_EXCEPTION_ERROR_TEXT1);
        }
        return null;
    }
}
