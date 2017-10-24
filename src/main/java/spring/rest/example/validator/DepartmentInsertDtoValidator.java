package spring.rest.example.validator;

import org.springframework.stereotype.Component;
import spring.rest.example.dto.DepartmentInsertDto;
import spring.rest.example.exception.ServiceValidationException;

import javax.annotation.PostConstruct;
import java.util.regex.Pattern;

@Component
public class DepartmentInsertDtoValidator implements Validator<DepartmentInsertDto> {

    private Pattern pattern;

    @PostConstruct
    public void init() {
        pattern = Pattern.compile("^[a-zA-Z]{5,}$");
    }

    @Override
    public void validate(DepartmentInsertDto data) {

        String name = data.getName();
        if (name == null || name.isEmpty()) {
            throw new ServiceValidationException("Deparment name is invalid.", "Provided department name is null or empty.");
        }

        if (!pattern.asPredicate().test(name)) {
            throw new ServiceValidationException("Department name is invalid.", "Department name should contains only letters[a-zA-Z] and the minimum length should be 5.");
        }
    }
}
