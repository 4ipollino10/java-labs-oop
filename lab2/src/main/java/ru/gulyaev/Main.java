package ru.gulyaev;

import org.apache.log4j.Logger;
import ru.gulyaev.util.Constants;
import ru.gulyaev.exceptions.BadInputFileException;
import ru.gulyaev.executors.Executor;
import ru.gulyaev.executors.FileExecutor;
import ru.gulyaev.executors.SISExecutor;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Executor executor;
        log.info(Constants.START_MESSAGE);
        if (args.length > Constants.MAX_INPUT_ARGS_AMOUNT) {
            log.error(Constants.ARGS_AMOUNT_EXCEPTION);
            return;
        }
        else if (args.length == Constants.MAX_INPUT_ARGS_AMOUNT) {
            try {
                executor = new FileExecutor(args[Constants.FILE_NAME_ARG]);

            } catch (BadInputFileException e) {
                log.error(Constants.INPUT_FILE_EXCEPTION);
                System.out.println(e.getMessage());
                return;
            }
        }
        else {
            executor = new SISExecutor();
        }

        executor.execute();
        log.info(Constants.END_MESSAGE);
    }
}

