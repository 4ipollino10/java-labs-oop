package ru.gulyaev.exceptions;

public class ParseException extends Exception{
    public ParseException(String errorText){
        super(errorText);
    }
}
