package spring.rest.example.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class ServiceException extends RuntimeException {

    private HttpStatus httpStatus;
    private int code;
    private String message;
    private String detailedMessage;

}
