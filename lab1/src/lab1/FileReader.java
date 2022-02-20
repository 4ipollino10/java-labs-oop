package lab1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileReader {
    private static final String LINE_END = " ";
    private static final String BAD_INPUT_FILE_ERROR = "Can't open input file";

    private String _file_name;

    private BufferedReader _reader;

    private StringParser _string_parser;

    public FileReader(String file_name, StringParser string_parser) throws BadInputFileException {
        _file_name = file_name;
        try{
            _reader = new BufferedReader(new InputStreamReader(new FileInputStream(_file_name), StandardCharsets.UTF_8));
        }catch (IOException e){
            throw new BadInputFileException(BAD_INPUT_FILE_ERROR);
        }
        _string_parser = string_parser;
    }

    public void readFile()throws IOException{
        String str = Constants.EMPTY_STR;
        while (str != null) {
            str = _reader.readLine();
            if (str != null) {
                str += LINE_END;
            } else {
                return;
            }
            _string_parser.parseString(str);
        }
    }

    public void destructor() throws IOException {
        _reader.close();
    }
}