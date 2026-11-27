package tn.esprit.attendance_service.services;

import tn.esprit.attendance_service.entities.AttendanceRecord;
import tn.esprit.attendance_service.entities.Justification;
import tn.esprit.attendance_service.repositories.AttendanceRecordRepository;
import tn.esprit.attendance_service.repositories.JustificationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class JustificationService {

    private final JustificationRepository justificationRepository;
    private final AttendanceRecordRepository attendanceRecordRepository;

    // Constructor injection
    public JustificationService(
            JustificationRepository justificationRepository,
            AttendanceRecordRepository attendanceRecordRepository) {
        this.justificationRepository = justificationRepository;
        this.attendanceRecordRepository = attendanceRecordRepository;
    }

    // Get all justifications
    public List<Justification> getAllJustifications() {
        return justificationRepository.findAll();
    }

    // Get a justification by ID
    public Justification getJustificationById(Long id) {
        return justificationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Justification not found with ID: " + id
                ));
    }

    // Get a justification by attendance record ID
    public Justification getJustificationByRecordId(Long recordId) {

        attendanceRecordRepository.findById(recordId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Attendance record not found with ID: " + recordId
                ));

        return justificationRepository.findByRecord_Id(recordId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No justification found for record ID: " + recordId
                ));
    }

    // Get justifications by status
    public List<Justification> getJustificationsByStatus(String status) {
        return justificationRepository.findByStatus(status);
    }

    // Create a justification for an attendance record
    public Justification createJustification(
            Long recordId, Justification justification) {

        AttendanceRecord record = attendanceRecordRepository.findById(recordId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Attendance record not found with ID: " + recordId
                ));

        // One record can have only one justification
        if (justificationRepository.existsByRecord_Id(recordId)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A justification already exists for this attendance record"
            );
        }

        justification.setId(null);
        justification.setRecord(record);

        return justificationRepository.save(justification);
    }

    // Update an existing justification
    public Justification updateJustification(
            Long id, Justification updatedJustification) {

        Justification existingJustification = getJustificationById(id);

        existingJustification.setReason(updatedJustification.getReason());
        existingJustification.setSubmittedDate(
                updatedJustification.getSubmittedDate()
        );
        existingJustification.setStatus(updatedJustification.getStatus());

        return justificationRepository.save(existingJustification);
    }

    // Delete a justification
    public void deleteJustification(Long id) {

        Justification existingJustification = getJustificationById(id);

        justificationRepository.delete(existingJustification);
    }
}