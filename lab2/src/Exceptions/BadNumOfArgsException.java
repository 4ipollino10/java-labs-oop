package Exceptions;

public class BadNumOfArgsException extends Exception{
    public BadNumOfArgsException(String errorText){
        super(errorText);
    }
}
