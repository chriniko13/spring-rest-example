package spring.rest.example.validator;

import org.springframework.stereotype.Component;
import spring.rest.example.dto.DepartmentInsertDto;
import spring.rest.example.exception.ServiceValidationException;

@Component
public class DepartmentInsertDtoValidator implements Validator<DepartmentInsertDto> {

    @Override
    public void validate(DepartmentInsertDto data) {

        String name = data.getName();
        if (name == null || name.isEmpty()) {
            throw new ServiceValidationException("Deparment name is invalid.", "Provided department name is null or empty.");
        }

    }
}
