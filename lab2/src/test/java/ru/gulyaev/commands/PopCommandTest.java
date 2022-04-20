package ru.gulyaev.commands;

import org.junit.jupiter.api.Test;
import ru.gulyaev.util.Constants;
import ru.gulyaev.util.contexts.Context;
import ru.gulyaev.exceptions.BadNumOfArgsException;
import ru.gulyaev.exceptions.EmptyStackSectionException;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PopCommandTest {
    private final PopCommand popCommand = new PopCommand(new ArrayList<>(Arrays.asList("POP".split(" "))));

    PopCommandTest() throws BadNumOfArgsException {}

    @Test
    void executeWorkTest2()throws EmptyStackSectionException {
        Context context = new Context();

        context.push(1.0);
        context.push(2.0);
        popCommand.execute(context);

        assertEquals(1.0, context.pop());
    }

    @Test
    void executeEmptyStackSectionExceptionTest1(){
        Context context = new Context();

        Exception exception = assertThrows(EmptyStackSectionException.class, () -> popCommand.execute(context));

        String expectedMessage = Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }
}