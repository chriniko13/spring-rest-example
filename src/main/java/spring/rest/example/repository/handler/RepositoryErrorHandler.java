package spring.rest.example.repository.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spring.rest.example.exception.ServiceBusinessException;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

@Aspect
public class RepositoryErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryErrorHandler.class);

    private Map<Predicate<Throwable>, Function<Throwable, ServiceBusinessException>> errorTransformers;

    @PostConstruct
    public void init() {
        // Note: order of declaration matters.
        errorTransformers = new LinkedHashMap<>();

        // Note: default error transformer...
        errorTransformers.put(error -> true, error -> new ServiceBusinessException(error.getMessage(), null, error));

    }


    @Around("within(@spring.rest.example.repository.handler.RepositoryErrorHandling *)")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info("\n\nRepositoryErrorHandler#handle() is running!");
        logger.info("RepositoryErrorHandler#handle() --- hijacked method : " + joinPoint.getSignature().getName());
        logger.info("RepositoryErrorHandler#handle() --- hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

        try {

            logger.info("RepositoryErrorHandler#handle() --- Around before is running!");

            Object proceed = joinPoint.proceed();//continue on the intercepted method

            logger.info("RepositoryErrorHandler#handle() --- Around after is running!");

            return proceed;

        } catch (Throwable error) {
            logger.info("RepositoryErrorHandler#handle() --- error occurred!");

            throw errorTransformers
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().test(error))
                    .findAny()
                    .get()
                    .getValue()
                    .apply(error);

        }
    }

}
