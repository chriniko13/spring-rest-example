package spring.rest.example.resource;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.rest.example.domain.Employee;
import spring.rest.example.dto.EmployeeDto;
import spring.rest.example.dto.EmployeeInsertDto;
import spring.rest.example.dto.EmployeeUpdateDto;
import spring.rest.example.service.EmployeeService;
import spring.rest.example.validator.Validator;

import java.lang.reflect.Type;
import java.util.Collection;

@RestController
@RequestMapping("/resources")
public class EmployeeResource {

    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;
    private final Validator<EmployeeInsertDto> employeeInsertDtoValidator;
    private final Validator<EmployeeUpdateDto> employeeUpdateDtoValidator;

    @Autowired
    public EmployeeResource(EmployeeService employeeService,

                            ModelMapper modelMapper,
                            Validator<EmployeeInsertDto> employeeInsertDtoValidator,
                            Validator<EmployeeUpdateDto> employeeUpdateDtoValidator) {
        this.employeeService = employeeService;
        this.modelMapper = modelMapper;
        this.employeeInsertDtoValidator = employeeInsertDtoValidator;
        this.employeeUpdateDtoValidator = employeeUpdateDtoValidator;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public void save(@RequestBody EmployeeInsertDto employeeInsertDto) {
        employeeInsertDtoValidator.validate(employeeInsertDto);
        employeeService.save(employeeInsertDto);
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

    @RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.PUT)
    public void update(@PathVariable("employeeId") Long employeeId, @RequestBody EmployeeUpdateDto employeeUpdateDto) {

        employeeUpdateDtoValidator.validate(employeeUpdateDto);
        employeeService.update(employeeId, employeeUpdateDto);
    }

    @RequestMapping(value = "/employees/{employeeId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("employeeId") Long id) {
        employeeService.delete(id);
    }
}
