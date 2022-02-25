package tests;

import Commands.DivCommand;
import Constants.Constants;
import Contexts.Context;
import Exceptions.BadNumOfArgsException;
import Exceptions.DivisionByZeroException;
import Exceptions.EmptyStackSectionException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DivCommandTest {
    private final DivCommand divCommand = new DivCommand(new ArrayList<>(Arrays.asList("/".split(""))));

    DivCommandTest() throws BadNumOfArgsException{}


    @Test
    void executeWorkTest1() throws EmptyStackSectionException, DivisionByZeroException {
        Context context = new Context();

        context.push(4.0);
        context.push(2.0);

        divCommand.execute(context);

        assertEquals(0.5, context.pop());

    }

    @Test
    void executeEmptyStackSectionExceptionTest1(){
        Context context = new Context();

        Exception exception = assertThrows(EmptyStackSectionException.class, () -> divCommand.execute(context));

        String expectedMessage = Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);

    }

    @Test
    void executeEmptyStackSectionExceptionTest2(){
        Context context = new Context();

        context.push(1.0);
        Exception exception = assertThrows(EmptyStackSectionException.class, () -> divCommand.execute(context));

        String expectedMessage = Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);

        assertEquals(1.0, context.pop());
    }

    @Test
    void executeDivisionByZeroExceptionTest2(){
        Context context = new Context();

        context.push(0.0);
        context.push(1.0);
        Exception exception = assertThrows(DivisionByZeroException.class, () -> divCommand.execute(context));

        String expectedMessage = Constants.DIVISION_BY_ZERO_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);

        assertEquals(1.0, context.pop());
        assertEquals(0.0, context.pop());
    }
}