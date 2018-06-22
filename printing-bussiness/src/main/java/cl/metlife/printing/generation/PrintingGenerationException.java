package cl.metlife.printing.generation;

public class PrintingGenerationException extends RuntimeException{

    public PrintingGenerationException(String message) {
        super(message);
    }

    public PrintingGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}
