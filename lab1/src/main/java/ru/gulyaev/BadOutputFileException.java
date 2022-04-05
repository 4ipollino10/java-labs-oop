package ru.gulyaev;

public class BadOutputFileException extends Throwable{
    public BadOutputFileException(String errorText){
        super(errorText);
    }
}