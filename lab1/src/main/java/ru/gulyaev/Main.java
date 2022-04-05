package ru.gulyaev;

public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length != Constants.MAX_NUM_OF_ARGS){
            System.out.println(Constants.ERROR_TEXT_1);
            return;
        }
        Context context = new Context();
        StringParser string_parser = new StringParser(context);
        String input_file_name = args[Constants.INPUT_FILE_ARG];
        String output_file_name = args[Constants.OUTPUT_FILE_ARG];
        FileReader fileReader;
        try{
            fileReader = new FileReader(input_file_name, string_parser);
        }catch (BadInputFileException e){
            System.out.println(e.getMessage());
            return;
        }
        fileReader.readFile();

        CSVMaker CSVMaker;
        try{
            CSVMaker = new CSVMaker(context, output_file_name);
        }catch (BadOutputFileException e){
            System.out.println(e.getMessage());
            return;
        }
        CSVMaker.makeOutPutFile();

        fileReader.destructor();
        CSVMaker.destructor();

    }
}