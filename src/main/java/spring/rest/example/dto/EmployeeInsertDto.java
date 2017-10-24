package spring.rest.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInsertDto {

    private String firstname;
    private String initials;
    private String surname;

    private Long departmentId;

}
