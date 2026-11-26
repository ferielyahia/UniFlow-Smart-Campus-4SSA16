
package tn.esprit.student_service.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicYear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String label;

    private LocalDate startDate;

    private LocalDate endDate;

    @JsonIgnore
    @OneToMany(mappedBy = "academicYear")
    private Set<Enrollment> enrollments;
}
