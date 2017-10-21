package spring.rest.example.service;

import org.springframework.stereotype.Service;
import spring.rest.example.domain.Employee;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public void save(Employee employee) {

    }

    @Override
    public List<Employee> list() {
        return null;
    }

    @Override
    public Employee find(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Employee employee) {

    }
}
