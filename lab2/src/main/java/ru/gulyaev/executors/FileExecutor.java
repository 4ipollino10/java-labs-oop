package ru.gulyaev.executors;

import org.apache.log4j.Logger;
import ru.gulyaev.factorys.Factory;
import ru.gulyaev.commands.Command;
import ru.gulyaev.util.Constants;
import ru.gulyaev.util.contexts.Context;
import ru.gulyaev.exceptions.*;

import java.io.*;

import java.util.Arrays;
import java.util.List;

public class FileExecutor implements Executor{
    private static final Logger log = Logger.getLogger(FileExecutor.class);


    private final BufferedReader _reader;

    private List<Command> _commands;

    public FileExecutor(String fileName) throws BadInputFileException {
        try {
            _reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        }catch (FileNotFoundException e) {
            throw new BadInputFileException(Constants.BAD_INPUT_FILE_EXCEPTION_ERROR_TEXT);
        }
        log.info(Constants.FILE_EXECUTOR_START_MESSAGE);
    }

    public void execute(){
        Context context = new Context();
        log.info(Constants.LOG_READ_INFO_3);
        try{
            parse();
        }catch (ParseException e){
            System.out.println(e.getMessage());
            return;
        }
        log.info(Constants.LOG_EXECUTE_INFO_1);
        for(Command command : _commands) {
            try{
                String res = command.execute(context);
                if (res != null) {
                    log.info(Constants.EXECUTION_COMPLETED);
                    System.out.print(res + System.lineSeparator());
                }
            }catch (Exception e){
                log.error(Constants.EXECUTION_ERROR);
                System.out.println(e.getMessage());
                return;
            }
        }
    }

    public void parse() throws ParseException {
        String line;
        try{
            while((line = _reader.readLine()) != null){
                if(line.charAt(0) == Constants.COMMENT){
                    continue;
                }
                List<String> args = Arrays.asList(line.split(Constants.SPACE));
                String command = args.get(Constants.COMMAND_ARG);
                log.info(Constants.LOG_READ_INFO_2);
                _commands.add(Factory.getInstance().getCommand(command, args));
            }
            close();
        } catch(Exception e){
            log.error(Constants.PROPERTIES_ERROR);
            System.out.println(e.getMessage());
            throw new ParseException(Constants.PARSE_EXCEPTION_ERROR_TEXT);
        }

    }

    private void close()throws IOException{
        _reader.close();
    }
}
