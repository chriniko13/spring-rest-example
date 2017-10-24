package spring.rest.example.service;

import spring.rest.example.domain.Employee;
import spring.rest.example.dto.EmployeeInsertDto;
import spring.rest.example.dto.EmployeeUpdateDto;

import java.util.Collection;

public interface EmployeeService {

    void save(EmployeeInsertDto employee);

    Collection<Employee> list();

    Employee find(Long id);

    void delete(Long id);

    void update(Long employeeId, EmployeeUpdateDto employee);
}
