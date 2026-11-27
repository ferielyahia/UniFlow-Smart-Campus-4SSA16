package tn.esprit.courseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.courseservice.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
