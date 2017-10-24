package spring.rest.example.validator;

import org.springframework.stereotype.Component;
import spring.rest.example.dto.EmployeeInsertDto;

@Component
public class EmployeeInsertDtoValidator extends EmployeeDtoValidator implements Validator<EmployeeInsertDto> {


    @Override
    public void validate(EmployeeInsertDto data) {

        String firstname = data.getFirstname();
        String surname = data.getSurname();

        validate(firstname, surname);
    }
}
