package spring.rest.example.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.rest.example.domain.Employee;
import spring.rest.example.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class EmployeeResource {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public void save(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> list() {
        return employeeService.list();
    }

    @RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.GET)
    public Employee find(@PathVariable("employeeId") Long id) {
        return employeeService.find(id);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.PUT)
    public void update(@RequestBody Employee employee) {
        employeeService.update(employee);
    }


    @RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("employeeId") Long id) {
        employeeService.delete(id);
    }
}
