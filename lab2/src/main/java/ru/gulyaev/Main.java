package ru.gulyaev;

import ru.gulyaev.Constants.Constants;
import ru.gulyaev.Exceptions.BadInputFileException;
import ru.gulyaev.Executors.Executor;
import ru.gulyaev.Executors.FileExecutor;
import ru.gulyaev.Executors.SISExecutor;

import java.io.IOException;

public class Main {
    private static final String className = Main.class.getSimpleName();

    public static void main(String[] args) {
        Executor executor = null;
        if (args.length > Constants.MAX_INPUT_ARGS_AMOUNT) {
            return;
        }
        else if (args.length == Constants.MAX_INPUT_ARGS_AMOUNT) {
            try {
                executor = new FileExecutor(args[Constants.FILE_NAME_ARG]);

            } catch (BadInputFileException e) {
                System.out.println(e.getMessage());
                return;
            }
        }
        else {
            executor = new SISExecutor();
        }

        executor.execute();
    }
}

