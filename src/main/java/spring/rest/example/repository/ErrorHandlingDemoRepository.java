package spring.rest.example.repository;

import org.springframework.stereotype.Repository;
import spring.rest.example.repository.handler.RepositoryErrorHandling;

import javax.persistence.PersistenceException;

@RepositoryErrorHandling
@Repository
public class ErrorHandlingDemoRepository {

    public void test() {
        throw new PersistenceException("error occurred");
    }

}
