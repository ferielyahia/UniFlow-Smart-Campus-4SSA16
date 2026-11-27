package tn.esprit.courseservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.courseservice.entity.Course;
import tn.esprit.courseservice.entity.Subject;
import tn.esprit.courseservice.repository.CourseRepository;
import tn.esprit.courseservice.repository.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    public CourseController(CourseRepository courseRepository, SubjectRepository subjectRepository) {
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        return courseRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        if (course.getTitle() == null || course.getSubject() == null || course.getSubject().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Subject subject = subjectRepository.findById(course.getSubject().getId()).orElse(null);
        if (subject == null) {
            return ResponseEntity.badRequest().build();
        }
        course.setId(null);
        course.setSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course data) {
        Course existing = courseRepository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (data.getTitle() == null) {
            return ResponseEntity.badRequest().build();
        }
        existing.setTitle(data.getTitle());
        existing.setDescription(data.getDescription());
        existing.setCredits(data.getCredits());
        if (data.getSubject() != null && data.getSubject().getId() != null) {
            Subject subject = subjectRepository.findById(data.getSubject().getId()).orElse(null);
            if (subject == null) {
                return ResponseEntity.badRequest().build();
            }
            existing.setSubject(subject);
        }
        return ResponseEntity.ok(courseRepository.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!courseRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        courseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
