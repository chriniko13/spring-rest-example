package spring.rest.example.exception;

class ServiceException extends RuntimeException {

    ServiceException() {
    }

    ServiceException(String message) {
        super(message);
    }

    ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    ServiceException(Throwable cause) {
        super(cause);
    }
}
