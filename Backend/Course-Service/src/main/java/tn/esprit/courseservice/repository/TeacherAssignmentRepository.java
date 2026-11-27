package tn.esprit.courseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.courseservice.entity.TeacherAssignment;

public interface TeacherAssignmentRepository extends JpaRepository<TeacherAssignment, Long> {
}
