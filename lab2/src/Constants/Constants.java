package Constants;

public class Constants {
    public static final int MAX_DEFAULT_COMMAND_ARGS_AMOUNT = 1;
    public static final int MAX_DEFINE_COMMAND_ARGS_AMOUNT = 3;
    public static final int MAX_PUSH_COMMAND_ARGS_AMOUNT = 2;
    public static final int MAX_INPUT_ARGS_AMOUNT = 1;
    public static final int COMMAND_ARG = 0;
    public static final int FILE_NAME_ARG = 0;
    public static final int VALUE_ARG = 2;
    public static final int DEFINE_ARG = 1;


    public static final Character COMMENT = '#';


    public static final String BAD_AMOUNT_OF_COMMAND_ARGS_EXCEPTION_ERROR_TEXT = "Bad amount of command args!";
    public static final String EMPTY_STACK_EXCEPTION_ERROR_TEXT = "Stack is empty!";
    public static final String DIVISION_BY_ZERO_EXCEPTION_ERROR_TEXT = "Division by zero!";
    public static final String CALCULATING_RADICAL_FROM_NEGATIVE_NUM_EXCEPTION_ERROR_TEXT = "Radical from negative number!";
    public static final String EMPTY_VAR_EXCEPTION_ERROR_TEXT = "Trying to define nothing!";
    public static final String BAD_INPUT_FILE_EXCEPTION_ERROR_TEXT = "Can't open input file!";
    public static final String COMMAND_NOT_FOUND_EXCEPTION_ERROR_TEXT = "There's no such command you wanted to execute -> ";
    public static final String PARSE_EXCEPTION_ERROR_TEXT = "Parsing file failed!";
    public static final String SPACE = " ";
    public static final String COMMANDS_PATH = "commands.properties";
    public static final String CONFIG_PATH = "config.properties";
    public static final String COMMAND_CLASS_PATH = "COMMAND_CLASS_PATH";
    public static final String ENDL = "\n";
    public static final String EMPTY_STR = "";

    public static final String SIS_STARTING_MESSAGE = "Enter your command's here, enter EXIT to quit.";
    public static final String EXIT_COMMAND = "EXIT";

    public static final int PUSH_VALUE_ARG = 1;
}
