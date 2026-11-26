package tn.esprit.student_service.controllers;

import tn.esprit.student_service.entities.AcademicYear;
import tn.esprit.student_service.services.AcademicYearService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/academic-years")
public class AcademicYearController {

    private final AcademicYearService academicYearService;

    public AcademicYearController(AcademicYearService academicYearService) {
        this.academicYearService = academicYearService;
    }

    // Ajouter une année académique
    @PostMapping
    public ResponseEntity<AcademicYear> addAcademicYear(
            @RequestBody AcademicYear academicYear) {
        return new ResponseEntity<>(
                academicYearService.addAcademicYear(academicYear),
                HttpStatus.CREATED
        );
    }

    // Récupérer toutes les années académiques
    @GetMapping
    public List<AcademicYear> getAllAcademicYears() {
        return academicYearService.getAllAcademicYears();
    }

    // Récupérer une année académique par ID
    @GetMapping("/{id}")
    public ResponseEntity<AcademicYear> getAcademicYearById(
            @PathVariable Long id) {
        return academicYearService.getAcademicYearById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Modifier une année académique
    @PutMapping("/{id}")
    public ResponseEntity<AcademicYear> updateAcademicYear(
            @PathVariable Long id,
            @RequestBody AcademicYear academicYear) {

        if (academicYearService.getAcademicYearById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                academicYearService.updateAcademicYear(id, academicYear)
        );
    }

    // Supprimer une année académique
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAcademicYear(@PathVariable Long id) {

        if (academicYearService.getAcademicYearById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        academicYearService.deleteAcademicYear(id);
        return ResponseEntity.noContent().build();
    }
}
