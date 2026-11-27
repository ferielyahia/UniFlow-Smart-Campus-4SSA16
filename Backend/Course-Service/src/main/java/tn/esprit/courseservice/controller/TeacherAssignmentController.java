package tn.esprit.courseservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.courseservice.entity.Course;
import tn.esprit.courseservice.entity.TeacherAssignment;
import tn.esprit.courseservice.repository.CourseRepository;
import tn.esprit.courseservice.repository.TeacherAssignmentRepository;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class TeacherAssignmentController {

    private final TeacherAssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    public TeacherAssignmentController(TeacherAssignmentRepository assignmentRepository,
                                       CourseRepository courseRepository) {
        this.assignmentRepository = assignmentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<TeacherAssignment> getAll() {
        return assignmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherAssignment> getById(@PathVariable Long id) {
        return assignmentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TeacherAssignment> create(@RequestBody TeacherAssignment assignment) {
        if (assignment.getTeacherId() == null || assignment.getCourse() == null
                || assignment.getCourse().getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Course course = courseRepository.findById(assignment.getCourse().getId()).orElse(null);
        if (course == null) {
            return ResponseEntity.badRequest().build();
        }
        assignment.setId(null);
        assignment.setCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentRepository.save(assignment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherAssignment> update(@PathVariable Long id,
                                                    @RequestBody TeacherAssignment data) {
        TeacherAssignment existing = assignmentRepository.findById(id).orElse(null);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (data.getTeacherId() == null) {
            return ResponseEntity.badRequest().build();
        }
        existing.setTeacherId(data.getTeacherId());
        existing.setRole(data.getRole());
        return ResponseEntity.ok(assignmentRepository.save(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!assignmentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        assignmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
