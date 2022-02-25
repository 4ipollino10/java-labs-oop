package Commands;

import Contexts.Context;
import Exceptions.*;

import java.util.List;

public abstract class Command {
    private List<String> _args;

    public Command(List<String> args) throws BadNumOfArgsException{
        _args = args;
    }

    public List<String> getArgs(){
        return _args;
    }

    public abstract String execute(Context context) throws
            EmptyStackSectionException,
            DivisionByZeroException,
            MathException,
            EmptyVarException,
            MapException,
            BadVarNameException;

}
