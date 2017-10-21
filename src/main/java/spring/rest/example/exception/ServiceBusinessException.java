package spring.rest.example.exception;

public class ServiceBusinessException extends ServiceException {

    public ServiceBusinessException() {
    }

    public ServiceBusinessException(String message) {
        super(message);
    }

    public ServiceBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceBusinessException(Throwable cause) {
        super(cause);
    }
}
