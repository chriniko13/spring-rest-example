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
        value = {
                @NamedQuery(name = "Department.findAll", query = "select d from Department as d"),
                @NamedQuery(name = "Department.findByName", query = "select d from Department as d where d.name = :name")
        }
)

public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private Instant creationDate;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Employee> employees;

}
