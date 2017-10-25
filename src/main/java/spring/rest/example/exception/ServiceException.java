package spring.rest.example.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ServiceException extends RuntimeException {

    private HttpStatus httpStatus;
    private int code;
    private String message;
    private String detailedMessage;


    ServiceException(HttpStatus httpStatus,
                            int code,
                            String message,
                            String detailedMessage) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.detailedMessage = detailedMessage;
    }

    ServiceException(HttpStatus httpStatus,
                            int code,
                            String message,
                            String detailedMessage,
                            Throwable cause) {
        super(cause);
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
        this.detailedMessage = detailedMessage;
    }

}
