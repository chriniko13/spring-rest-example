package spring.rest.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.rest.example.domain.Department;
import spring.rest.example.domain.Employee;
import spring.rest.example.dto.EmployeeInsertDto;
import spring.rest.example.dto.EmployeeUpdateDto;
import spring.rest.example.exception.ServiceValidationException;
import spring.rest.example.repository.DepartmentRepository;
import spring.rest.example.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Collection;

@Transactional(
        propagation = Propagation.REQUIRES_NEW,
        isolation = Isolation.REPEATABLE_READ
)

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void save(EmployeeInsertDto employeeInsertDto) {

        String firstname = employeeInsertDto.getFirstname();
        String initials = employeeInsertDto.getInitials();
        String surname = employeeInsertDto.getSurname();

        Employee employee = Employee.builder().firstname(firstname).initials(initials).surname(surname).build();

        Long departmentId = employeeInsertDto.getDepartmentId();
        if (departmentId != null) {
            bind(employee, departmentId);
        }

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
        Employee employee = ensureEmployeeExists(id);

        if (employee.getDepartment() != null) {
            throw new ServiceValidationException("Could not delete employee.",
                    "Employee is attached to department=" + employee.getDepartment().getName() + ", remove her from the department first and try again.");
        }

        employeeRepository.delete(id);
    }

    @Override
    public void update(Long employeeId, EmployeeUpdateDto employeeUpdateDto) {

        Employee retrievedEmployee = ensureEmployeeExists(employeeId);

        retrievedEmployee.setFirstname(employeeUpdateDto.getFirstname());
        retrievedEmployee.setInitials(employeeUpdateDto.getInitials());
        retrievedEmployee.setSurname(employeeUpdateDto.getSurname());

        Long departmentId = employeeUpdateDto.getDepartmentId();
        if (departmentId != null) {
            bind(retrievedEmployee, departmentId);
        } else {
            retrievedEmployee.setDepartment(null);
        }

        employeeRepository.update(retrievedEmployee);
    }

    private Employee ensureEmployeeExists(Long employeeId) {
        Employee employee = employeeRepository.get(employeeId);
        if (employee == null) {
            throw new ServiceValidationException("Employee(id = " + employeeId + ") not found.", "Could not found Employee with id: " + employeeId);
        }
        return employee;
    }

    private Department ensureDepartmentExists(Long departmentId) {
        Department department = departmentRepository.get(departmentId);
        if (department == null) {
            throw new ServiceValidationException("Department(id = " + departmentId + ") not found.", "Could not found department with id: " + departmentId);
        }
        return department;
    }

    private void bind(Employee employee, Long departmentId) {

        Department department = ensureDepartmentExists(departmentId);

        employee.setDepartment(department);

        if (department.getEmployees() == null) {
            department.setEmployees(new ArrayList<>());
            department.getEmployees().add(employee);
            departmentRepository.update(department);
        }
    }
}
