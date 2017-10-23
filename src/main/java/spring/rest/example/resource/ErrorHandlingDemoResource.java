package spring.rest.example.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.rest.example.exception.ServiceBusinessException;
import spring.rest.example.exception.ServiceValidationException;

@RestController
@RequestMapping("/resources/error-demonstration")
public class ErrorHandlingDemoResource {

    @RequestMapping(value = "/first-case", method = RequestMethod.GET)
    public void testErrorOne() {
        throw new ServiceBusinessException("Business method execution failed.",
                "The selected business operation could not be performed, try again later.");
    }

    @RequestMapping(value = "/second-case", method = RequestMethod.GET)
    public void testErrorTwo() {
        throw new ServiceValidationException("Invalid request data provided.",
                "The provided data are not valid, so business operation could not be performed.");
    }

    @RequestMapping(value = "/third-case", method = RequestMethod.GET)
    public void testErrorThree() {
        throw new IllegalStateException("Application not in correct state.");
    }

}
