package tn.esprit.student_service.controllers;

import tn.esprit.student_service.entities.Enrollment;
import tn.esprit.student_service.services.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // Ajouter une inscription
    @PostMapping
    public ResponseEntity<Enrollment> addEnrollment(
            @RequestBody Enrollment enrollment) {
        return new ResponseEntity<>(
                enrollmentService.addEnrollment(enrollment),
                HttpStatus.CREATED
        );
    }

    // Récupérer toutes les inscriptions
    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }

    // Récupérer une inscription par ID
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(
            @PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Modifier une inscription
    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(
            @PathVariable Long id,
            @RequestBody Enrollment enrollment) {

        if (enrollmentService.getEnrollmentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                enrollmentService.updateEnrollment(id, enrollment)
        );
    }

    // Supprimer une inscription
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {

        if (enrollmentService.getEnrollmentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        enrollmentService.deleteEnrollment(id);
        return ResponseEntity.noContent().build();
    }
}
