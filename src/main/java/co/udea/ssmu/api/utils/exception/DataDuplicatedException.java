package co.udea.ssmu.api.utils.exception;

/**
 * Exception for duplicated data in the application
 */
public class DataDuplicatedException extends GeneralRuntimeException {

    private static final long serialVersionUID = 1L;

    public DataDuplicatedException() {
        super("Data is duplicated.");
    }

    public DataDuplicatedException(String message) {
        super(message);
    }
}
