package spring.rest.example.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import spring.rest.example.domain.Employee;
import spring.rest.example.repository.handler.RepositoryErrorHandling;

import javax.persistence.*;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collection;

@RepositoryErrorHandling
@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);

    @Value("${platform.deployed.zoneid}")
    private ZoneId zoneId;

    @PersistenceContext(synchronization = SynchronizationType.SYNCHRONIZED, type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public Collection<Employee> list() {
        logger.info("EmployeeRepositoryImpl#list --- called!");

        TypedQuery<Employee> namedQuery = em.createNamedQuery("Employee.findAll", Employee.class);
        return namedQuery.getResultList();
    }

    @Override
    public void save(Employee employee) {
        logger.info("EmployeeRepositoryImpl#save --- called!");

        employee.setCreationDate(Instant.now(Clock.system(zoneId)));
        em.persist(employee);
    }

    @Override
    public Employee get(Long id) {
        logger.info("EmployeeRepositoryImpl#get --- called!");

        return em.find(Employee.class, id);
    }

    @Override
    public void update(Employee employee) {
        logger.info("EmployeeRepositoryImpl#update --- called!");

        em.merge(employee);
    }

    @Override
    public void delete(Long id) {
        logger.info("EmployeeRepositoryImpl#delete --- called!");

        Employee employee = em.find(Employee.class, id);
        em.remove(employee);
    }
}
