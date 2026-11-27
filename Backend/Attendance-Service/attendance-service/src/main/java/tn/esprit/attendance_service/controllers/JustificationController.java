package tn.esprit.attendance_service.controllers;

import tn.esprit.attendance_service.entities.Justification;
import tn.esprit.attendance_service.services.JustificationService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/justifications")
public class JustificationController {

    private final JustificationService justificationService;

    // Constructor injection
    public JustificationController(
            JustificationService justificationService) {
        this.justificationService = justificationService;
    }

    // GET: Retrieve all justifications
    @GetMapping
    public ResponseEntity<List<Justification>> getAllJustifications() {
        return ResponseEntity.ok(
                justificationService.getAllJustifications()
        );
    }

    // GET: Retrieve a justification by ID
    @GetMapping("/{id}")
    public ResponseEntity<Justification> getJustificationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                justificationService.getJustificationById(id)
        );
    }

    // GET: Retrieve a justification by attendance record ID
    @GetMapping("/record/{recordId}")
    public ResponseEntity<Justification> getJustificationByRecordId(
            @PathVariable Long recordId) {

        return ResponseEntity.ok(
                justificationService.getJustificationByRecordId(recordId)
        );
    }

    // GET: Retrieve justifications by status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Justification>> getJustificationsByStatus(
            @PathVariable String status) {

        return ResponseEntity.ok(
                justificationService.getJustificationsByStatus(status)
        );
    }

    // POST: Create a justification for an attendance record
    @PostMapping("/record/{recordId}")
    public ResponseEntity<Justification> createJustification(
            @PathVariable Long recordId,
            @Valid @RequestBody Justification justification) {

        Justification createdJustification =
                justificationService.createJustification(
                        recordId, justification
                );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdJustification);
    }

    // PUT: Update an existing justification
    @PutMapping("/{id}")
    public ResponseEntity<Justification> updateJustification(
            @PathVariable Long id,
            @Valid @RequestBody Justification justification) {

        return ResponseEntity.ok(
                justificationService.updateJustification(
                        id, justification
                )
        );
    }

    // DELETE: Delete a justification
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJustification(
            @PathVariable Long id) {

        justificationService.deleteJustification(id);

        return ResponseEntity.noContent().build();
    }
}
