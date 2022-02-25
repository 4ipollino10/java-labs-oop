package Commands;

import Constants.Constants;
import Contexts.Context;
import Exceptions.*;

import java.util.List;

public class DefineCommand extends Command{

    public DefineCommand(List<String> args)throws BadNumOfArgsException {
        super(args);

        if(args.size() != Constants.MAX_DEFINE_COMMAND_ARGS_AMOUNT){
            throw new BadNumOfArgsException(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_EXCEPTION_ERROR_TEXT);
        }
    }

    @Override
    public String execute(Context context) throws
            EmptyVarException,
            BadVarNameException {

        double value;
        try{
            if(!context.isNormalName(getArgs().get(Constants.DEFINE_ARG))){
                throw new BadVarNameException(Constants.BAD_VAR_NAME_ERROR_TEXT);
            }

            value = Double.parseDouble(getArgs().get(Constants.VALUE_ARG));

            context.addValue(getArgs().get(Constants.DEFINE_ARG), value);
        }catch (NumberFormatException e){
            try {

                value = context.searchValue(getArgs().get(Constants.VALUE_ARG));

                context.addValue(getArgs().get(Constants.DEFINE_ARG), value);
            }catch (MapException ex){
                throw new EmptyVarException(Constants.EMPTY_VAR_EXCEPTION_ERROR_TEXT);
            }
        }



        return null;
    }
}
