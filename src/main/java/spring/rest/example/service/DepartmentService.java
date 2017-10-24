package spring.rest.example.service;

import spring.rest.example.domain.Department;
import spring.rest.example.dto.DepartmentInsertDto;
import spring.rest.example.dto.DepartmentUpdateDto;

import java.util.Collection;

public interface DepartmentService {

    void save(DepartmentInsertDto department);

    Collection<Department> list();

    Department find(Long id);

    void update(Long departmentId, DepartmentUpdateDto department);

    void delete(Long id);
}
