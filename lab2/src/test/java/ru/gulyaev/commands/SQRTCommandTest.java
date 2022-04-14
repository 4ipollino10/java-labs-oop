package ru.gulyaev.commands;

import org.junit.jupiter.api.Test;
import ru.gulyaev.Commands.SQRTCommand;
import ru.gulyaev.Constants.Constants;
import ru.gulyaev.Contexts.Context;
import ru.gulyaev.Exceptions.BadNumOfArgsException;
import ru.gulyaev.Exceptions.EmptyStackSectionException;
import ru.gulyaev.Exceptions.MathException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SQRTCommandTest {
    private final SQRTCommand sqrtCommand = new SQRTCommand(new ArrayList<>(Arrays.asList("SQRT".split(" "))));

    SQRTCommandTest()throws BadNumOfArgsException {}

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

        Exception exception = assertThrows(EmptyStackSectionException.class, () -> sqrtCommand.execute(context));

        String expectedMessage = Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);

    }


}