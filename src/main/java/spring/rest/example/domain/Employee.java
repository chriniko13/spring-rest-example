package spring.rest.example.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id"})
@ToString

@Entity
@Table(name = "employee")


@NamedQueries(

        @NamedQuery(name = "Employee.findAll", query = "select e from Employee as e")

)

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String initials;
    private String surname;

    private Instant creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Department department;

}
