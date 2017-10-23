package spring.rest.example.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import spring.rest.example.domain.Department;

import javax.persistence.*;
import java.time.ZoneId;
import java.util.Collection;

@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentRepositoryImpl.class);

    @Value("${platform.deployed.zoneid}")
    private ZoneId zoneId;

    @PersistenceContext(synchronization = SynchronizationType.SYNCHRONIZED, type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public Collection<Department> list() {
        TypedQuery<Department> namedQuery = em.createNamedQuery("Department.findAll", Department.class);
        return namedQuery.getResultList();
    }

    @Override
    public void save(Department department) {
        em.persist(department);
    }

    @Override
    public Department get(Long id) {
        return em.find(Department.class, id);
    }

    @Override
    public void update(Department department) {
        em.merge(department);
    }

    @Override
    public void delete(Long id) {
        Department department = em.find(Department.class, id);
        em.remove(department);
    }
}
