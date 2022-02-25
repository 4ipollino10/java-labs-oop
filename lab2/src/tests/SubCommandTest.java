package tests;

import Commands.SubCommand;
import Constants.Constants;
import Contexts.Context;
import Exceptions.BadNumOfArgsException;
import Exceptions.EmptyStackSectionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SubCommandTest {
    private final SubCommand subCommand = new SubCommand(new ArrayList<>(Arrays.asList("-".split(""))));

    SubCommandTest() throws BadNumOfArgsException{}


    @Test
    void executeWorkTest1() throws EmptyStackSectionException {
        Context context = new Context();

        context.push(3.0);
        context.push(6.0);

        subCommand.execute(context);

        assertEquals(3.0, context.getStackPeek());

    }

    @Test
    void executeExceptionTest1() {
        Context context = new Context();

        Exception exception = assertThrows(EmptyStackSectionException.class, () -> {
            subCommand.execute(context);
        });

        String expectedMessage = Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void executeEmptyStackSectionExceptionTest2() {
        Context context = new Context();

        context.push(1.0);

        Exception exception = assertThrows(EmptyStackSectionException.class, () -> {
            subCommand.execute(context);
        });

        String expectedMessage = Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);

        assertEquals(1.0, context.pop());
    }

}