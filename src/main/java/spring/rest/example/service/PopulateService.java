package spring.rest.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.rest.example.domain.Department;
import spring.rest.example.domain.Employee;
import spring.rest.example.repository.DepartmentRepository;
import spring.rest.example.repository.EmployeeRepository;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collection;

@Transactional(
        propagation = Propagation.REQUIRES_NEW,
        isolation = Isolation.REPEATABLE_READ
)

@Service
public class PopulateService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Value("${platform.deployed.zoneid}")
    private ZoneId zoneId;

    @Autowired
    public PopulateService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public void populateDepartments() {
        Instant now = Instant.now(Clock.system(zoneId));

        // create 5 departments...
        Department softEngsDept = Department.builder().creationDate(now).name("Software Engineers").build();
        Department basDept = Department.builder().creationDate(now).name("Business Analysts").build();
        Department qasDept = Department.builder().creationDate(now).name("Quality Assurance Engineers").build();
        Department setsDept = Department.builder().creationDate(now).name("Software Engineers in Test").build();
        Department sdlsDept = Department.builder().creationDate(now).name("Software Delivery Leads").build();

        departmentRepository.save(softEngsDept);
        departmentRepository.save(basDept);
        departmentRepository.save(qasDept);
        departmentRepository.save(setsDept);
        departmentRepository.save(sdlsDept);

    }

    public void populateEmployees() {

        Instant now = Instant.now(Clock.system(zoneId));

        //create 10 employees per department...
        Collection<Department> departments = departmentRepository.list();

        for (Department department : departments) {

            for (int i = 1; i <= 3; i++) {

                Employee employee = Employee.builder()
                        .creationDate(now)
                        .firstname("Firstname " + i)
                        .initials("Initials " + i)
                        .surname("Surname " + i)
                        .department(department)
                        .build();

                employeeRepository.save(employee);

            }
        }

    }
}
