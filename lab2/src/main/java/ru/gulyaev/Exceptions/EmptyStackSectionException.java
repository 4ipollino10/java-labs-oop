package ru.gulyaev.Exceptions;

public class EmptyStackSectionException extends Exception {
    public EmptyStackSectionException(String errorText){
        super(errorText);
    }
}