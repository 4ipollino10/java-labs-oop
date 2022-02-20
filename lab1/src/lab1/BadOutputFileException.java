package lab1;

public class BadOutputFileException extends Throwable{
    public BadOutputFileException(String errorText){
        super(errorText);
    }
}
