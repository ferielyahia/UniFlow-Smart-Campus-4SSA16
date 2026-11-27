package tn.esprit.attendance_service.controllers;

import tn.esprit.attendance_service.entities.AttendanceSheet;
import tn.esprit.attendance_service.services.AttendanceSheetService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceSheetController {

    private final AttendanceSheetService attendanceSheetService;

    // Constructor injection
    public AttendanceSheetController(
            AttendanceSheetService attendanceSheetService) {
        this.attendanceSheetService = attendanceSheetService;
    }

    // GET: Retrieve all attendance sheets
    @GetMapping
    public ResponseEntity<List<AttendanceSheet>> getAllSheets() {
        return ResponseEntity.ok(
                attendanceSheetService.getAllSheets()
        );
    }

    // GET: Retrieve an attendance sheet by ID
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceSheet> getSheetById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceSheetService.getSheetById(id)
        );
    }

    // POST: Create a new attendance sheet
    @PostMapping
    public ResponseEntity<AttendanceSheet> createSheet(
            @Valid @RequestBody AttendanceSheet sheet) {

        AttendanceSheet createdSheet =
                attendanceSheetService.createSheet(sheet);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdSheet);
    }

    // PUT: Update an existing attendance sheet
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceSheet> updateSheet(
            @PathVariable Long id,
            @Valid @RequestBody AttendanceSheet sheet) {

        return ResponseEntity.ok(
                attendanceSheetService.updateSheet(id, sheet)
        );
    }

    // DELETE: Delete an attendance sheet
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSheet(
            @PathVariable Long id) {

        attendanceSheetService.deleteSheet(id);

        return ResponseEntity.noContent().build();
    }
}
