package spring.rest.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import spring.rest.example.exception.ServiceBusinessException;
import spring.rest.example.repository.ErrorHandlingDemoRepository;

@Transactional(
        propagation = Propagation.REQUIRES_NEW,
        isolation = Isolation.REPEATABLE_READ,
        rollbackFor = {ServiceBusinessException.class}
)
@Service
public class ErrorHandlingDemoService {

    private final ErrorHandlingDemoRepository errorHandlingDemoRepository;

    @Autowired
    public ErrorHandlingDemoService(ErrorHandlingDemoRepository errorHandlingDemoRepository) {
        this.errorHandlingDemoRepository = errorHandlingDemoRepository;
    }

    public void test() {
        errorHandlingDemoRepository.test();
    }

}
