package ru.gulyaev.Exceptions;

public class BadNumOfArgsException extends Exception{
    public BadNumOfArgsException(String errorText){
        super(errorText);
    }
}
