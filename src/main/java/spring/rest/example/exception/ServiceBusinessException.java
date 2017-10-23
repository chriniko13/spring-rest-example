package spring.rest.example.exception;

import org.springframework.http.HttpStatus;

public class ServiceBusinessException extends ServiceException {

    public ServiceBusinessException(String message, String detailedMessage) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, detailedMessage);
    }

}
