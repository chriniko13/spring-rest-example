package spring.rest.example.exception.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import spring.rest.example.error.RestErrorMessage;
import spring.rest.example.exception.ServiceException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<Object> handleInvalidRequest(ServiceException e, ServletWebRequest request) {
        RestErrorMessage error =
                new RestErrorMessage(e.getHttpStatus(),
                        e.getCode(),
                        e.getMessage(),
                        e.getDetailedMessage(),
                        e.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.OK, request);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleInvalidRequest_Default(Exception e, ServletWebRequest request) {
        RestErrorMessage error =
                new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        e.getMessage(),
                        null,
                        e.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, error, headers, HttpStatus.OK, request);
    }

}
