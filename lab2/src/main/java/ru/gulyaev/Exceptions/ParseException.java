package ru.gulyaev.Exceptions;

public class ParseException extends Exception{
    public ParseException(String errorText){
        super(errorText);
    }
}
