package tests;

import Commands.SQRTCommand;
import Constants.Constants;
import Contexts.Context;
import Exceptions.BadNumOfArgsException;
import Exceptions.DivisionByZeroException;
import Exceptions.EmptyStackSectionException;
import Exceptions.MathException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SQRTCommandTest {
    private final SQRTCommand sqrtCommand = new SQRTCommand(new ArrayList<>(Arrays.asList("SQRT".split(" "))));

    SQRTCommandTest()throws BadNumOfArgsException{}

    @Test
    void executeWorkTest1() throws EmptyStackSectionException, MathException {
        Context context = new Context();

        context.push(9.0);

        sqrtCommand.execute(context);

        assertEquals(3.0, context.pop());
    }

    @Test
    void executeMathExceptionTest1() {
        Context context = new Context();

        context.push(-1.0);

        Exception exception = assertThrows(MathException.class, () -> sqrtCommand.execute(context));

        String expectedMessage = Constants.CALCULATING_RADICAL_FROM_NEGATIVE_NUM_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);

        assertEquals(-1.0, context.pop());
    }

    @Test
    void executeEmptyStackSectionExceptionTest1() {
        Context context = new Context();

        Exception exception = assertThrows(MathException.class, () -> sqrtCommand.execute(context));

        String expectedMessage = Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);

    }


}