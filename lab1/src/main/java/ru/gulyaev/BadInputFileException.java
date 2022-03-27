package ru.gulyaev;


public class BadInputFileException extends Throwable {
    public BadInputFileException(String errorText){
        super(errorText);
    }
}
