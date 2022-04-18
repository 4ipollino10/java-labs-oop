package ru.gulyaev.exceptions;

public class DivisionByZeroException extends Exception{
    public DivisionByZeroException(String errorText){
        super(errorText);
    }
}
