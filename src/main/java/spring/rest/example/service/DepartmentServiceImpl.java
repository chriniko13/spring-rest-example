package spring.rest.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.rest.example.domain.Department;
import spring.rest.example.domain.Employee;
import spring.rest.example.dto.DepartmentInsertDto;
import spring.rest.example.dto.DepartmentUpdateDto;
import spring.rest.example.exception.ServiceValidationException;
import spring.rest.example.repository.DepartmentRepository;
import spring.rest.example.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Transactional(
        propagation = Propagation.REQUIRES_NEW,
        isolation = Isolation.REPEATABLE_READ
)

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void save(DepartmentInsertDto departmentInsertDto) {

        ensureDepartmentNotExists(departmentInsertDto);

        String departmentName = departmentInsertDto.getName();
        List<Long> employeeIds = departmentInsertDto.getEmployeeIds();

        Department departmentToSave = Department.builder().name(departmentName).employees(new ArrayList<>()).build();

        if (employeeIds != null && !employeeIds.isEmpty()) {
            bind(departmentToSave, employeeIds);
        }

        departmentRepository.save(departmentToSave);
    }

    @Override
    public Collection<Department> list() {
        return departmentRepository.list();
    }

    @Override
    public Department find(Long id) {
        return ensureDepartmentExists(id);
    }

    @Override
    public void update(Long departmentId, DepartmentUpdateDto departmentUpdateDto) {

        Department department = ensureDepartmentExists(departmentId);

        String departmentName = departmentUpdateDto.getName();
        List<Long> employeeIds = departmentUpdateDto.getEmployeeIds();

        department.setName(departmentName);

        if (employeeIds != null && !employeeIds.isEmpty()) {
            bind(department, employeeIds);
        } else {
            department.getEmployees().clear();
        }

        department.setName(departmentUpdateDto.getName());
        departmentRepository.update(department);
    }

    private void bind(Department department, List<Long> employeeIds) {
        for (Long employeeId : employeeIds) {

            Employee employee = employeeRepository.get(employeeId);
            if (employee == null) {
                throw new ServiceValidationException("Employee(id = " + employeeId + ") not found.", "Could not found employee with id: " + employeeId);
            }

            employee.setDepartment(department);
            department.getEmployees().add(employee);
        }
    }

    @Override
    public void delete(Long id) {
        ensureDepartmentExists(id);
        departmentRepository.delete(id);
    }

    private void ensureDepartmentNotExists(DepartmentInsertDto departmentInsertDto) {
        String deptName = departmentInsertDto.getName();
        Department department = getDepartmentByName(deptName);
        if (department != null) {
            throw new ServiceValidationException("Department(name = " + deptName + ") already exists.", "A department already exists with name: " + deptName);
        }
    }

    private Department ensureDepartmentExists(Long id) {
        Department department = getDepartment(id);
        if (department == null) {
            throw new ServiceValidationException("Department(id = " + id + ") not found.", "Could not found department with id: " + id);
        }
        return department;
    }

    private Department getDepartmentByName(String deptName) {
        return departmentRepository.findByName(deptName);
    }

    private Department getDepartment(Long id) {
        return departmentRepository.get(id);
    }
}
