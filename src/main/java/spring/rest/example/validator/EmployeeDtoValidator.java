package spring.rest.example.validator;

import spring.rest.example.exception.ServiceValidationException;

import javax.annotation.PostConstruct;
import java.util.regex.Pattern;

public class EmployeeDtoValidator {

    private Pattern pattern;

    @PostConstruct
    public void init() {
        pattern = Pattern.compile("^[a-zA-Z]{5,}$");
    }


    void validate(String firstname, String surname) {

        if (firstname == null || firstname.isEmpty()) {
            throw new ServiceValidationException("Firstname is invalid.", "Firstname is null or empty.");
        }

        if (surname == null || surname.isEmpty()) {
            throw new ServiceValidationException("Surname is invalid.", "Surname is null or empty.");
        }

        if (!pattern.asPredicate().test(firstname)) {
            throw new ServiceValidationException("Firstname is invalid.",
                    "Firstname should contains only letters[a-zA-Z] and the minimum length should be 5.");
        }

        if (!pattern.asPredicate().test(surname)) {
            throw new ServiceValidationException("Surname is invalid.",
                    "Surname should contains only letters[a-zA-Z] and the minimum length should be 5.");
        }

    }
}
