package ru.gulyaev.Exceptions;

public class EmptyVarException extends Exception{
    public EmptyVarException(String errorText){
        super(errorText);
    }
}