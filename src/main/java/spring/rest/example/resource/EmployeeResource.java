package spring.rest.example.resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.rest.example.domain.Employee;
import spring.rest.example.dto.EmployeeDto;
import spring.rest.example.service.EmployeeService;

import java.lang.reflect.Type;
import java.util.Collection;

@RestController
@RequestMapping("/resources")
public class EmployeeResource {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @Autowired
    public EmployeeResource(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public void save(@RequestBody Employee employee) {
        employeeService.save(employee);
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public @ResponseBody
    Collection<EmployeeDto> list() {

        Collection<Employee> employees = employeeService.list();
        Type listType = new TypeToken<Collection<EmployeeDto>>() {
        }.getType();

        return modelMapper.map(employees, listType);

    }

    @RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.GET)
    public EmployeeDto find(@PathVariable("employeeId") Long id) {

        Employee employee = employeeService.find(id);
        return modelMapper.map(employee, EmployeeDto.class);
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
