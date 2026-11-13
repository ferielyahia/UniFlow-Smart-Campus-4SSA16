
package tn.esprit.student_service.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;

    private LocalDate dateOfBirth;

    @Column(unique = true, nullable = false)
    private String registrationNumber;

    private String status;

    @OneToMany(mappedBy = "student")
    private Set<Enrollment> enrollments;
}
