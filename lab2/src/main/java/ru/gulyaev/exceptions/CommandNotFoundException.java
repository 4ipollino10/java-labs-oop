package ru.gulyaev.exceptions;

public class CommandNotFoundException extends Exception{
    public CommandNotFoundException(String errorText){
        super(errorText);
    }
}
