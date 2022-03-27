package ru.gulyaev.Exceptions;

public class DivisionByZeroException extends Exception{
    public DivisionByZeroException(String errorText){
        super(errorText);
    }
}
