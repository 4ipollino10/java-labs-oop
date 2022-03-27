package ru.gulyaev.Commands;

import Constants.Constants;
import Contexts.Context;
import Exceptions.BadNumOfArgsException;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class DefineCommand extends Command {
    public DefineCommand(List<String> args) throws BadNumOfArgsException {
        super(args);
        if(args.size() != Constants.MAX_DEFINE_COMMAND_ARGS_AMOUNT){
            throw new BadNumOfArgsException(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_ERROR_TEXT);
        }
    }


    @Override
    public String execute(Context context) {
        return null;
    }
}
