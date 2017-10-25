package spring.rest.example.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import spring.rest.example.repository.handler.RepositoryErrorHandling;

import javax.persistence.PersistenceException;

@RepositoryErrorHandling
@Repository
public class ErrorHandlingDemoRepository {

    private static final Logger logger = LoggerFactory.getLogger(ErrorHandlingDemoRepository.class);


    public void test() {
        logger.info("ErrorHandlingDemoRepository#test --- called!");

        throw new PersistenceException("error occurred");
    }

}
