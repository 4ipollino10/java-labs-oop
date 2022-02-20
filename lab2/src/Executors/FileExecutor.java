package Executors;

import Commands.Command;
import Constants.Constants;
import Exceptions.BadInputFileException;
import Exceptions.BadNumOfArgsException;
import Exceptions.ParseException;

import java.io.*;
import java.util.List;

public class FileExecutor implements Executor{

    private Reader _reader;

    private List<Command> _commands;

    public FileExecutor(String fileName) throws BadInputFileException {
        try {
            _reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        }catch (FileNotFoundException e) {
            throw new BadInputFileException(Constants.BAD_INPUT_FILE_ERROR_TEXT);
        }
    }

    public void execute(){

    }

    public void parse() throws ParseException {
        String line;
        try{

        }
    }
}
