package spring.rest.example.service;

import spring.rest.example.domain.Employee;

import java.util.Collection;

public interface EmployeeService {

    void save(Employee employee);

    Collection<Employee> list();

    Employee find(Long id);

    void delete(Long id);

    void update(Employee employee);
}
