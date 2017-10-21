package spring.rest.example.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import spring.rest.example.domain.Employee;

import javax.persistence.*;
import java.time.ZoneId;
import java.util.Collection;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeRepositoryImpl.class);

    @Value("${platform.deployed.zoneid}")
    private ZoneId zoneId;

    @PersistenceContext(synchronization = SynchronizationType.SYNCHRONIZED, type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public Collection<Employee> list() {
        TypedQuery<Employee> namedQuery = em.createNamedQuery("Employee.findAll", Employee.class);
        return namedQuery.getResultList();
    }

    @Override
    public void save(Employee employee) {
        em.persist(employee);
    }

    @Override
    public Employee get(Long id) {
        return em.find(Employee.class, id);
    }

    @Override
    public void update(Employee employee) {
        em.merge(employee);
    }

    @Override
    public void delete(Long id) {
        em.remove(this.get(id));
    }
}
