package spring.rest.example.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import spring.rest.example.domain.Department;
import spring.rest.example.repository.handler.RepositoryErrorHandling;

import javax.persistence.*;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collection;

@RepositoryErrorHandling
@Repository
public class DepartmentRepositoryImpl implements DepartmentRepository {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentRepositoryImpl.class);

    @Value("${platform.deployed.zoneid}")
    private ZoneId zoneId;

    @PersistenceContext(synchronization = SynchronizationType.SYNCHRONIZED, type = PersistenceContextType.TRANSACTION)
    private EntityManager em;

    @Override
    public Collection<Department> list() {
        logger.info("DepartmentRepositoryImpl#list --- called!");

        TypedQuery<Department> namedQuery = em.createNamedQuery("Department.findAll", Department.class);
        return namedQuery.getResultList();
    }

    @Override
    public void save(Department department) {
        logger.info("DepartmentRepositoryImpl#save --- called!");

        department.setCreationDate(Instant.now(Clock.system(zoneId)));
        em.persist(department);
    }

    @Override
    public Department get(Long id) {
        logger.info("DepartmentRepositoryImpl#get --- called!");

        return em.find(Department.class, id);
    }

    @Override
    public void update(Department department) {
        logger.info("DepartmentRepositoryImpl#update --- called!");

        em.merge(department);
    }

    @Override
    public void delete(Long id) {
        logger.info("DepartmentRepositoryImpl#delete --- called!");

        Department department = em.find(Department.class, id);
        em.remove(department);
    }

    @Override
    public Department findByName(String name) {
        logger.info("DepartmentRepositoryImpl#findByName --- called!");

        TypedQuery<Department> namedQuery = em.createNamedQuery("Department.findByName", Department.class);
        namedQuery.setParameter("name", name);

        try {
            return namedQuery.getSingleResult();
        } catch (NoResultException error) {
            return null;
        }
    }
}
