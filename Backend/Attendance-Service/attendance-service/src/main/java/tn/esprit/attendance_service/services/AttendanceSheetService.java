package tn.esprit.attendance_service.services;

import tn.esprit.attendance_service.entities.AttendanceSheet;
import tn.esprit.attendance_service.repositories.AttendanceSheetRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class AttendanceSheetService {

    private final AttendanceSheetRepository attendanceSheetRepository;

    // Constructor injection
    public AttendanceSheetService(
            AttendanceSheetRepository attendanceSheetRepository) {
        this.attendanceSheetRepository = attendanceSheetRepository;
    }

    // Get all attendance sheets
    public List<AttendanceSheet> getAllSheets() {
        return attendanceSheetRepository.findAll();
    }

    // Get an attendance sheet by ID
    public AttendanceSheet getSheetById(Long id) {
        return attendanceSheetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Attendance sheet not found with ID: " + id
                ));
    }

    // Create a new attendance sheet
    public AttendanceSheet createSheet(AttendanceSheet sheet) {

        if (attendanceSheetRepository.existsBySessionId(sheet.getSessionId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "An attendance sheet already exists for this session"
            );
        }

        // Associate every attendance record with its parent sheet
        if (sheet.getRecords() != null) {
            sheet.getRecords().forEach(record -> record.setSheet(sheet));
        }

        return attendanceSheetRepository.save(sheet);
    }

    // Update an existing attendance sheet
    public AttendanceSheet updateSheet(Long id, AttendanceSheet updatedSheet) {

        AttendanceSheet existingSheet = getSheetById(id);

        if (!Objects.equals(existingSheet.getSessionId(), updatedSheet.getSessionId())
                && attendanceSheetRepository.existsBySessionId(updatedSheet.getSessionId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "An attendance sheet already exists for this session"
            );
        }

        existingSheet.setSessionId(updatedSheet.getSessionId());
        existingSheet.setTeacherId(updatedSheet.getTeacherId());
        existingSheet.setSheetDate(updatedSheet.getSheetDate());
        existingSheet.setStatus(updatedSheet.getStatus());

        return attendanceSheetRepository.save(existingSheet);
    }

    // Delete an attendance sheet
    public void deleteSheet(Long id) {

        AttendanceSheet existingSheet = getSheetById(id);

        attendanceSheetRepository.delete(existingSheet);
    }
}
