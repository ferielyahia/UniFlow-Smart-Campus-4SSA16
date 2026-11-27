package tn.esprit.attendance_service.services;

import tn.esprit.attendance_service.entities.AttendanceRecord;
import tn.esprit.attendance_service.entities.AttendanceSheet;
import tn.esprit.attendance_service.repositories.AttendanceRecordRepository;
import tn.esprit.attendance_service.repositories.AttendanceSheetRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AttendanceRecordService {

    private final AttendanceRecordRepository recordRepository;
    private final AttendanceSheetRepository sheetRepository;

    // Constructor injection
    public AttendanceRecordService(
            AttendanceRecordRepository recordRepository,
            AttendanceSheetRepository sheetRepository) {
        this.recordRepository = recordRepository;
        this.sheetRepository = sheetRepository;
    }

    // Get all attendance records
    public List<AttendanceRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    // Get a record by ID
    public AttendanceRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Attendance record not found: " + id
                ));
    }

    // Get all records belonging to a sheet
    public List<AttendanceRecord> getRecordsBySheetId(Long sheetId) {

        sheetRepository.findById(sheetId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Attendance sheet not found: " + sheetId
                ));

        return recordRepository.findBySheet_Id(sheetId);
    }

    // Create a record inside an existing attendance sheet
    public AttendanceRecord createRecord(
            Long sheetId, AttendanceRecord record) {

        AttendanceSheet sheet = sheetRepository.findById(sheetId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Attendance sheet not found: " + sheetId
                ));

        // Prevent duplicate records for the same student and sheet
        if (recordRepository.existsBySheet_IdAndStudentId(
                sheetId, record.getStudentId())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student already has a record in this sheet"
            );
        }

        record.setId(null);
        record.setSheet(sheet);

        return recordRepository.save(record);
    }

    // Update an existing record
    public AttendanceRecord updateRecord(
            Long id, AttendanceRecord updatedRecord) {

        AttendanceRecord existingRecord = getRecordById(id);

        Long sheetId = existingRecord.getSheet().getId();

        if (!Objects.equals(
                existingRecord.getStudentId(),
                updatedRecord.getStudentId())
                && recordRepository.existsBySheet_IdAndStudentId(
                sheetId, updatedRecord.getStudentId())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student already has a record in this sheet"
            );
        }

        existingRecord.setStudentId(updatedRecord.getStudentId());
        existingRecord.setStatus(updatedRecord.getStatus());
        existingRecord.setRemark(updatedRecord.getRemark());

        return recordRepository.save(existingRecord);
    }

    // Delete an attendance record
    public void deleteRecord(Long id) {

        AttendanceRecord existingRecord = getRecordById(id);

        recordRepository.delete(existingRecord);
    }
}