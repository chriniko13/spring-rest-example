package spring.rest.example.exception;

public class ServiceValidationException extends ServiceException {

    public ServiceValidationException() {
    }

    public ServiceValidationException(String message) {
        super(message);
    }

    public ServiceValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceValidationException(Throwable cause) {
        super(cause);
    }
}
