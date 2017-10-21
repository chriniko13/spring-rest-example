package spring.rest.example.service;

import spring.rest.example.domain.Employee;

import java.util.List;

public interface EmployeeService {

    void save(Employee employee);

    List<Employee> list();

    Employee find(Long id);

    void delete(Long id);

    void update(Employee employee);
}
