package spring.rest.example.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter

public class RestErrorMessage {

    private HttpStatus status;
    private int code;
    private String message;
    private String detailedMessage;
    private String exceptionMessage;

}
