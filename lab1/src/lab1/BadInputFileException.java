package lab1;

public class BadInputFileException extends Throwable {
    public BadInputFileException(String errorText){
        super(errorText);
    }
}
