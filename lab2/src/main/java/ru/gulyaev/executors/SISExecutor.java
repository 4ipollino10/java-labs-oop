package ru.gulyaev.executors;

import org.apache.log4j.Logger;
import ru.gulyaev.factorys.Factory;
import ru.gulyaev.commands.Command;
import ru.gulyaev.util.Constants;
import ru.gulyaev.util.contexts.Context;
import ru.gulyaev.exceptions.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SISExecutor implements Executor{
    private static final Logger log = Logger.getLogger(SISExecutor.class);

    {
        log.info(Constants.SIS_EXECUTOR_START_MESSAGE);
    }
    @Override
    public void execute() {
        Context context = new Context();

        Scanner scanner = new Scanner(System.in);

        System.out.println(Constants.SIS_STARTING_MESSAGE);
        while(true) {
            String line = scanner.nextLine();
            if (line.equals(Constants.EXIT_COMMAND)) {
                scanner.close();
                return;
            }else if(!line.equals(Constants.EMPTY_STR) && line.charAt(0) == Constants.COMMENT ){

                continue;
            }

            List<String> args = Arrays.asList(line.split(Constants.SPACE));
            String commandName = args.get(Constants.COMMAND_ARG);
            log.info(Constants.LOG_READ_INFO_2);
            Command command;

            try {
                command = Factory.getInstance().getCommand(commandName, args);
            }catch (CommandNotFoundException e) {
                log.error(Constants.BAD_COMMAND_EXCEPTION);
                System.out.println(e.getMessage());
                continue;
            }catch (InvocationTargetException e){
                log.error(Constants.COMMAND_ARGS_ERROR);
                System.out.println(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_EXCEPTION_ERROR_TEXT);
                continue;
            }catch(Exception e){
                log.error(Constants.EXECUTION_ERROR);
                e.printStackTrace();
                System.out.println(e.getMessage());
                return;
            }
            try{
                String result = command.execute(context);
                if(result != null){
                    System.out.println(result);
                }
            }catch (Exception e){
                e.printStackTrace();
                log.error(Constants.EXECUTION_ERROR);
                System.out.println(e.getMessage());

            }
        }

    }
}
