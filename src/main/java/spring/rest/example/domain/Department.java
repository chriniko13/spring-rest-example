package spring.rest.example.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = {"name"})
@ToString

@Entity
@Table(name = "department")

@NamedQueries(

        @NamedQuery(name = "Department.findAll", query = "select d from Department as d")

)

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Instant creationDate;

    @OneToMany(
            mappedBy = "department",
            fetch = FetchType.EAGER
    )
    private List<Employee> employees;

}
