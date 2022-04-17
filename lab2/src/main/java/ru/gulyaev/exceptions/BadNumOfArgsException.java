package ru.gulyaev.exceptions;

public class BadNumOfArgsException extends Exception{
    public BadNumOfArgsException(String errorText){
        super(errorText);
    }
}
