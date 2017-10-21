package spring.rest.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.rest.example.domain.Department;
import spring.rest.example.repository.DepartmentRepository;

import java.util.Collection;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public Collection<Department> list() {
        return departmentRepository.list();
    }

    @Override
    public Department find(Long id) {
        return departmentRepository.get(id);
    }

    @Override
    public void update(Department department) {
        departmentRepository.update(department);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.delete(id);
    }
}
