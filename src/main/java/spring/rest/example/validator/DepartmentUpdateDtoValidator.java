package spring.rest.example.validator;

import org.springframework.stereotype.Component;
import spring.rest.example.dto.DepartmentUpdateDto;
import spring.rest.example.exception.ServiceValidationException;

@Component
public class DepartmentUpdateDtoValidator implements Validator<DepartmentUpdateDto> {

    @Override
    public void validate(DepartmentUpdateDto data) {

        String name = data.getName();
        if (name == null || name.isEmpty()) {
            throw new ServiceValidationException("Deparment name is invalid.", "Provided department name is null or empty.");
        }
    }
}
