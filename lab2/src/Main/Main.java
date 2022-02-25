package Main;

import Constants.Constants;
import Exceptions.BadInputFileException;
import Executors.Executor;
import Executors.FileExecutor;
import Executors.SISExecutor;


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
