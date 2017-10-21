package spring.rest.example.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;
import spring.rest.example.exception.ServiceBusinessException;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DbInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DbInitializer.class);

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() {

        final ClassPathResource resource = new ClassPathResource("scripts/db.sql");

        try (Connection connection = dataSource.getConnection()) {

            ScriptUtils.executeSqlScript(connection, resource);


        } catch (Exception error) {
            logger.error("SetupDatabase#runNativeSql --- error occurred.", error);
            throw new ServiceBusinessException("Could not execute initialization of database.", error);
        }
    }


}
