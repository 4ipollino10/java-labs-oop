package Executors;

import Commands.Command;
import Constants.Constants;
import Contexts.Context;
import Exceptions.CommandNotFoundException;
import Factorys.CommandFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SISExecutor implements Executor{

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
            Command command = null;

            try {
                command = CommandFactory.getInstance().getCommand(commandName, args);
            }catch (CommandNotFoundException e) {
                System.out.println(e.getMessage());
                continue;
            }catch (InvocationTargetException e){
                System.out.println(Constants.BAD_AMOUNT_OF_COMMAND_ARGS_EXCEPTION_ERROR_TEXT);
                continue;
            }catch(Exception e){
                System.out.println(e.getMessage());
                return;
            }
            try{
                String result = command.execute(context);
                if(result != null){
                    System.out.println(result);
                }
            }catch (Exception e){
                System.out.println(e.getMessage());

            }
        }

    }
}
