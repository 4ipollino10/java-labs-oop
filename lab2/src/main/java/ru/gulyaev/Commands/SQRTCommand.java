package ru.gulyaev.Commands;


import ru.gulyaev.Constants.Constants;
import ru.gulyaev.Contexts.Context;
import ru.gulyaev.Exceptions.BadNumOfArgsException;
import ru.gulyaev.Exceptions.EmptyStackSectionException;
import ru.gulyaev.Exceptions.MathException;

import java.util.EmptyStackException;
import java.util.List;


import static java.lang.Math.sqrt;

public class SQRTCommand extends Command{
    public SQRTCommand(List<String> args) throws BadNumOfArgsException {
        super(args);
        if(args.size() != Constants.MAX_DEFAULT_COMMAND_ARGS_AMOUNT){
            throw new BadNumOfArgsException(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_EXCEPTION_ERROR_TEXT);
        }
    }

    @Override
    public String execute(Context context) throws
            EmptyStackSectionException,
            MathException {

        double firstVal;
        try {
            firstVal = context.pop();
        }catch (EmptyStackException e){
            throw new EmptyStackSectionException(Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT);
        }
        if(firstVal < 0){
            context.push(firstVal);
            throw new MathException(Constants.CALCULATING_RADICAL_FROM_NEGATIVE_NUM_EXCEPTION_ERROR_TEXT);
        }
        context.addValue(sqrt(firstVal));
        return null;
    }
}
