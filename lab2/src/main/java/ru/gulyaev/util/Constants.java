package ru.gulyaev.util;

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

    public static final String DIVISION_BY_ZERO_EXCEPTION_ERROR_TEXT = "Division by zero!";
    public static final String CALCULATING_RADICAL_FROM_NEGATIVE_NUM_EXCEPTION_ERROR_TEXT = "Radical from negative number!";


    public static final String EMPTY_STACK_EXCEPTION_ERROR_TEXT = "Stack is empty!";
    public static final String EMPTY_VAR_EXCEPTION_ERROR_TEXT = "Trying to define nothing!";
    public static final String EMPTY_VAR_EXCEPTION_ERROR_TEXT1 = "Trying to push nothing!";
    public static final String BAD_AMOUNT_OF_COMMAND_ARGS_EXCEPTION_ERROR_TEXT = "Bad amount of command args!";
    public static final String BAD_INPUT_FILE_EXCEPTION_ERROR_TEXT = "Can't open input file!";
    public static final String BAD_VAR_NAME_ERROR_TEXT = "Bad definition argument name, use standard way of naming variables!";
    public static final String COMMAND_NOT_FOUND_EXCEPTION_ERROR_TEXT = "There's no such command you wanted to execute -> ";
    public static final String PARSE_EXCEPTION_ERROR_TEXT = "Parsing file failed!";




    public static final String SPACE = " ";
    public static final String COMMANDS_PATH = "command.properties";
    public static final String CONFIG_PATH = "config.properties";
    public static final String COMMAND_CLASS_PATH = "COMMAND_CLASS_PATH";
    public static final String EMPTY_STR = "";

    public static final String VAR_NAME_FILTER = "^[a-zA-Z_][a-zA-Z0-9_]*$";

    public static final String SIS_STARTING_MESSAGE = "Enter your command's here, enter EXIT to quit.";
    public static final String EXIT_COMMAND = "EXIT";

    public static final int PUSH_VALUE_ARG = 1;



    public static final String START_MESSAGE = "So, it begins...";
    public static final String END_MESSAGE = "We did it!";
    public static final String ARGS_AMOUNT_EXCEPTION = "Bad Amount Of Args Exception";
    public static final String BAD_COMMAND_EXCEPTION = "No such command";
    public static final String COMMAND_ARGS_ERROR = "Bad amount of command args";
    public static final String INPUT_FILE_EXCEPTION = "Bad Input File Exception";
    public static final String FILE_EXECUTOR_START_MESSAGE = "File executor has started his work";
    public static final String SIS_EXECUTOR_START_MESSAGE = "SIS executor has started his work";
    public static final String LOG_READ_INFO_3 = "Reading file started";
    public static final String LOG_READ_INFO_2 = "Reading command started";
    public static final String LOG_EXECUTE_INFO_1 = "Execution started";
    public static final String EXECUTION_ERROR = "Execution ended with execution exception";
    public static final String EXECUTION_COMPLETED = "Execution ended well";
    public static final String PROPERTIES_ERROR = "Failed reading properties file";




}