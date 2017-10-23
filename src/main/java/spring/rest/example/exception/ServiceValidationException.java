package spring.rest.example.exception;

import org.springframework.http.HttpStatus;

public class ServiceValidationException extends ServiceException {

    public ServiceValidationException(String message, String detailedMessage) {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), message, detailedMessage);
    }
}
