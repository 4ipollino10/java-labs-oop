package ru.gulyaev.commands;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import ru.gulyaev.util.Constants;
import ru.gulyaev.util.contexts.Context;
import ru.gulyaev.exceptions.BadNumOfArgsException;
import ru.gulyaev.exceptions.EmptyStackSectionException;

import java.util.ArrayList;
import java.util.Arrays;

class AddCommandTest {
    private final AddCommand addCommand = new AddCommand(new ArrayList<>(Arrays.asList("+".split(""))));

    AddCommandTest() throws BadNumOfArgsException {}

    @Test
    void executeWorkTest1() throws EmptyStackSectionException {
        Context context = new Context();

        context.push(1.0);
        context.push(1.0);

        addCommand.execute(context);

        assertEquals(2.0, context.pop());
    }

    @Test
    void executeExceptionTest1() {
        Context context = new Context();

        Exception exception = assertThrows(EmptyStackSectionException.class, () -> {
            addCommand.execute(context);
        });

        String expectedMessage = Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);
    }

    @Test
    void executeEmptyStackSectionExceptionTest2() {
        Context context = new Context();

        context.push(1.0);

        Exception exception = assertThrows(EmptyStackSectionException.class, () -> addCommand.execute(context));

        String expectedMessage = Constants.EMPTY_STACK_EXCEPTION_ERROR_TEXT;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage);

        assertEquals(1.0, context.pop());
    }
}