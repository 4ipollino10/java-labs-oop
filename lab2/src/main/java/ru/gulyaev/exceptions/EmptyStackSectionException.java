package ru.gulyaev.exceptions;

public class EmptyStackSectionException extends Exception {
    public EmptyStackSectionException(String errorText){
        super(errorText);
    }
}