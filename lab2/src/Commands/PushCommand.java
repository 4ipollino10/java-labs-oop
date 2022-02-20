package Commands;

import Contexts.Context;
import Constants.Constants;
import Exceptions.BadNumOfArgsException;
import Exceptions.EmptyStackSectionException;
import Exceptions.EmptyVarException;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PushCommand extends Command{
    public PushCommand(List<String> args) throws BadNumOfArgsException {
        super(args);
        if(args.size() != Constants.MAX_PUSH_COMMAND_ARGS_AMOUNT){
            throw new BadNumOfArgsException(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_ERROR_TEXT);
        }
    }

    @Override
    public String execute(Context context) throws EmptyVarException {
        double val;
        if(getArgs().get(Constants.PUSH_VALUE_ARG) != null){
            val = Double.parseDouble(getArgs().get(Constants.PUSH_VALUE_ARG));
            context.addValue(val);
        }else if(context.getVars().containsKey(getArgs().get(Constants.PUSH_VALUE_ARG))){
            val = context.getVars().get(getArgs().get(Constants.PUSH_VALUE_ARG));
            context.addValue(val);
        }else{
            throw new EmptyVarException(Constants.EMPTY_VAR_EXCEPTION_ERROR_TEXT);
        }
        return null;
    }
}
