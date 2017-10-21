package spring.rest.example.service;

import spring.rest.example.domain.Department;

import java.util.Collection;

public interface DepartmentService {

    void save(Department department);

    Collection<Department> list();

    Department find(Long id);

    void update(Department department);

    void delete(Long id);
}
