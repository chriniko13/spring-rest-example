package spring.rest.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.rest.example.domain.Employee;
import spring.rest.example.repository.EmployeeRepository;

import java.util.Collection;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Collection<Employee> list() {
        return employeeRepository.list();
    }

    @Override
    public Employee find(Long id) {
        return employeeRepository.get(id);
    }

    @Override
    public void delete(Long id) {
        employeeRepository.delete(id);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.update(employee);
    }
}
