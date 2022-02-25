package tests;

import Commands.MulCommand;
import Constants.Constants;
import Contexts.Context;
import Exceptions.BadNumOfArgsException;
import Exceptions.EmptyStackSectionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MulCommandTest {
    private final MulCommand mulCommand = new MulCommand(new ArrayList<>(Arrays.asList("*".split(""))));

    MulCommandTest() throws BadNumOfArgsException {}

    @Test
    void executeWorkTest1() throws EmptyStackSectionException {
        Context context = new Context();

        context.push(2.0);
        context.push(3.0);

        mulCommand.execute(context);

        assertEquals(6.0, context.pop());
    }

    @Test
    void executeExceptionTest1() {
        Context context = new Context();

        Exception exception = assertThrows(EmptyStackSectionException.class, () -> {
            mulCommand.execute(context);
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
            mulCommand.execute(context);
        });

        String expectedMessage = Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);

        assertEquals(1.0, context.pop());
    }
}