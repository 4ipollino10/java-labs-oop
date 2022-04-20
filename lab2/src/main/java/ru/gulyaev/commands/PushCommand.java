package ru.gulyaev.commands;

import ru.gulyaev.util.Constants;
import ru.gulyaev.util.contexts.Context;
import ru.gulyaev.exceptions.BadNumOfArgsException;
import ru.gulyaev.exceptions.EmptyVarException;
import ru.gulyaev.exceptions.MapException;

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


        Double val = new Scanner(this.getArgs().get(Constants.PUSH_VALUE_ARG)).nextDouble();
        if(val != null){

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