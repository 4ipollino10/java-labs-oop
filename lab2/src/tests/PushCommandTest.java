package tests;

import Commands.DefineCommand;
import Commands.PushCommand;
import Constants.Constants;
import Contexts.Context;
import Exceptions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PushCommandTest {

    @Test
    void executeWorkTest1() throws BadNumOfArgsException, EmptyVarException, MapException {
        PushCommand pushCommand = new PushCommand(new ArrayList<>(Arrays.asList("PUSH 9".split(" "))));
        Context context = new Context();
        pushCommand.execute(context);
        assertEquals(9.0, context.pop());
    }

    @Test
    void executeWorkTest3() throws BadNumOfArgsException, EmptyVarException, BadVarNameException, MapException {
        DefineCommand defineCommand = new DefineCommand(new ArrayList<>(Arrays.asList("DEFINE a 4".split(" "))));
        PushCommand pushCommand = new PushCommand(new ArrayList<>(Arrays.asList("PUSH a".split(" "))));
        Context context = new Context();
        defineCommand.execute(context);
        pushCommand.execute(context);
        assertEquals(4.0, context.getStackPeek());
    }

    @Test
    void executeEmptyVarExceptionTest1() throws BadNumOfArgsException {
        PushCommand pushCommand = new PushCommand(new ArrayList<>(Arrays.asList("PUSH a".split(" "))));
        Context context = new Context();
        Exception exception = assertThrows(EmptyVarException.class, () -> pushCommand.execute(context));

        String expectedMessage = Constants.EMPTY_VAR_EXCEPTION_ERROR_TEXT1;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }


}