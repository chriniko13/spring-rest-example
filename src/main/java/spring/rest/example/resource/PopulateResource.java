package spring.rest.example.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.rest.example.service.PopulateService;

@RestController
@RequestMapping("/populate")
public class PopulateResource {

    private final PopulateService populateService;

    @Autowired
    public PopulateResource(PopulateService populateService) {
        this.populateService = populateService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void init() {
        populateService.populateDepartments();
        populateService.populateEmployees();
    }
}
