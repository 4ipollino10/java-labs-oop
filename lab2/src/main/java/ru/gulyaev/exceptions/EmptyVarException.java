package ru.gulyaev.exceptions;

public class EmptyVarException extends Exception{
    public EmptyVarException(String errorText){
        super(errorText);
    }
}