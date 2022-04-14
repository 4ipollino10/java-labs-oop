package ru.gulyaev.Exceptions;

public class BadVarNameException extends Exception{
    public BadVarNameException(String errorText){
        super(errorText);
    }
}
