package tn.esprit.attendance_service.controllers;

import tn.esprit.attendance_service.entities.AttendanceRecord;
import tn.esprit.attendance_service.services.AttendanceRecordService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceRecordController {

    private final AttendanceRecordService attendanceRecordService;

    // Constructor injection
    public AttendanceRecordController(
            AttendanceRecordService attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
    }

    // GET: Retrieve all attendance records
    @GetMapping("/records")
    public ResponseEntity<List<AttendanceRecord>> getAllRecords() {
        return ResponseEntity.ok(
                attendanceRecordService.getAllRecords()
        );
    }

    // GET: Retrieve one record by ID
    @GetMapping("/records/{id}")
    public ResponseEntity<AttendanceRecord> getRecordById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceRecordService.getRecordById(id)
        );
    }

    // GET: Retrieve all records of one attendance sheet
    @GetMapping("/{sheetId}/records")
    public ResponseEntity<List<AttendanceRecord>> getRecordsBySheetId(
            @PathVariable Long sheetId) {

        return ResponseEntity.ok(
                attendanceRecordService.getRecordsBySheetId(sheetId)
        );
    }

    // POST: Add a record to an existing attendance sheet
    @PostMapping("/{sheetId}/records")
    public ResponseEntity<AttendanceRecord> createRecord(
            @PathVariable Long sheetId,
            @Valid @RequestBody AttendanceRecord record) {

        AttendanceRecord createdRecord =
                attendanceRecordService.createRecord(sheetId, record);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdRecord);
    }

    // PUT: Update an existing record
    @PutMapping("/records/{id}")
    public ResponseEntity<AttendanceRecord> updateRecord(
            @PathVariable Long id,
            @Valid @RequestBody AttendanceRecord record) {

        return ResponseEntity.ok(
                attendanceRecordService.updateRecord(id, record)
        );
    }

    // DELETE: Delete an attendance record
    @DeleteMapping("/records/{id}")
    public ResponseEntity<Void> deleteRecord(
            @PathVariable Long id) {

        attendanceRecordService.deleteRecord(id);

        return ResponseEntity.noContent().build();
    }
}
