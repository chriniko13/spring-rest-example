package spring.rest.example.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.rest.example.exception.ServiceBusinessException;
import spring.rest.example.exception.ServiceValidationException;
import spring.rest.example.service.ErrorHandlingDemoService;

@RestController
@RequestMapping("/resources/error-demonstration")
public class ErrorHandlingDemoResource {

    private final ErrorHandlingDemoService errorHandlingDemoService;

    @Autowired
    public ErrorHandlingDemoResource(ErrorHandlingDemoService errorHandlingDemoService) {
        this.errorHandlingDemoService = errorHandlingDemoService;
    }

    @RequestMapping(value = "/first-case", method = RequestMethod.GET)
    public void testErrorOne() {
        throw new ServiceBusinessException("Business method execution failed.",
                "The selected business operation could not be performed, try again later.", null);
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


    @RequestMapping(value = "/fourth-case", method = RequestMethod.GET)
    public void testErrorFourth() {
        errorHandlingDemoService.test();
    }

}
