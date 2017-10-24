package spring.rest.example.repository;

import spring.rest.example.domain.Department;

public interface DepartmentRepository extends GenericRepository<Long, Department> {


    Department findByName(String name);

}
