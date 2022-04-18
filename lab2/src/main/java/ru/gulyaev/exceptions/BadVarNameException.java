package ru.gulyaev.exceptions;

public class BadVarNameException extends Exception{
    public BadVarNameException(String errorText){
        super(errorText);
    }
}
