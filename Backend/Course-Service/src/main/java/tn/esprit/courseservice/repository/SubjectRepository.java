package tn.esprit.courseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.courseservice.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
