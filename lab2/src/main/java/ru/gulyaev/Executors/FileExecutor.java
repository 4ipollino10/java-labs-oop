package ru.gulyaev.Executors;

import ru.gulyaev.CommandFactory.Factory;
import ru.gulyaev.Commands.Command;
import ru.gulyaev.Constants.Constants;
import ru.gulyaev.Contexts.Context;
import ru.gulyaev.Exceptions.*;

import java.io.*;

import java.util.Arrays;
import java.util.List;

public class FileExecutor implements Executor{

    private final BufferedReader _reader;

    private List<Command> _commands;

    public FileExecutor(String fileName) throws BadInputFileException {
        try {
            _reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        }catch (FileNotFoundException e) {
            throw new BadInputFileException(Constants.BAD_INPUT_FILE_EXCEPTION_ERROR_TEXT);
        }
    }

    public void execute(){
        Context context = new Context();

        try{
            parse();
        }catch (ParseException e){
            System.out.println(e.getMessage());
            return;
        }

        for(Command command : _commands) {
            try{
                command.execute(context);
            }catch (Exception e){
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
                _commands.add(Factory.getInstance().getCommand(command, args));
            }
            close();
        } catch(Exception e){
            System.out.println(e.getMessage());
            throw new ParseException(Constants.PARSE_EXCEPTION_ERROR_TEXT);
        }

    }

    private void close()throws IOException{
        _reader.close();
    }
}
