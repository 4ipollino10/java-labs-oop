package ru.gulyaev;


import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class CSVMaker {
    private final Context _context;
    private final FileWriter _file_writer;
    String _file_name;
    private static final String HEADERS_LINE = "word,amount,frequency\n";
    private static final String SEPARATOR = ",";
    private static final String ENDL = "\n";
    private static final String BAD_OUTPUT_FILE_ERROR = "Can't open output file";

    public CSVMaker(Context context, String file_name) throws BadOutputFileException {
        _file_name = file_name;
        try{
            _file_writer = new FileWriter(_file_name);
        }catch (IOException e){
            throw new BadOutputFileException(BAD_OUTPUT_FILE_ERROR);
        }
        _context = context;
    }

    public void makeOutPutFile()throws IOException{
        printHeaders();
        List<Entry<String, Integer>> stat = _context.getSortedStat();
        for (Entry<String, Integer> stringIntegerEntry : stat) {
            String word = stringIntegerEntry.getKey();
            int amount = stringIntegerEntry.getValue();
            write(word, amount);
        }
    }

    public double getFrequency(int word_counter, int frequency){
        return (double)frequency / (double)word_counter;
    }

    public void write(String word, int amount)throws IOException {
        int word_counter = _context.getWordCounter();
        double frequency = getFrequency(word_counter, amount);
        String output_line = word + SEPARATOR + amount + SEPARATOR + frequency + ENDL;
        _file_writer.write(output_line);
    }

    public void printHeaders() throws IOException{
        _file_writer.write(HEADERS_LINE);
    }

    public void destructor() throws IOException {
        _file_writer.flush();
        _file_writer.close();
    }
}