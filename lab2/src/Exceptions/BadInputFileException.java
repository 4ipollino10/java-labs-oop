package Exceptions;

public class BadInputFileException extends Exception {
    public BadInputFileException(String errorText){
        super(errorText);
    }
}
