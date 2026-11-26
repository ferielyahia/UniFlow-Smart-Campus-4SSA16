package tn.esprit.student_service.repositories;

import tn.esprit.student_service.entities.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {
}
