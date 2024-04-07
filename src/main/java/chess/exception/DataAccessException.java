package chess.exception;

public class DataAccessException extends RuntimeException {
    public DataAccessException(final Throwable throwable) {
        super(throwable);
    }
}
