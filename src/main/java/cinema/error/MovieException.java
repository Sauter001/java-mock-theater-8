package cinema.error;

public class MovieException extends IllegalArgumentException {
    private static final String ERROR_PREFIX = "[ERROR] ";

    public MovieException(String message) {
        super(ERROR_PREFIX + message);
    }
}
