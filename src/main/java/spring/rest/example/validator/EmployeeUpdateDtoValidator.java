package spring.rest.example.validator;

import org.springframework.stereotype.Component;
import spring.rest.example.dto.EmployeeUpdateDto;

@Component
public class EmployeeUpdateDtoValidator extends EmployeeDtoValidator implements Validator<EmployeeUpdateDto> {


    @Override
    public void validate(EmployeeUpdateDto data) {
        String firstname = data.getFirstname();
        String surname = data.getSurname();

        validate(firstname, surname);

    }
}
