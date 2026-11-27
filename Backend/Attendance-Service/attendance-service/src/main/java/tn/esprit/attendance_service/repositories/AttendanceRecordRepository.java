package tn.esprit.attendance_service.repositories;

import tn.esprit.attendance_service.entities.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRecordRepository
        extends JpaRepository<AttendanceRecord, Long> {

    // Find all attendance records belonging to a student
    List<AttendanceRecord> findByStudentId(Long studentId);

    // Find all attendance records belonging to a sheet
    List<AttendanceRecord> findBySheet_Id(Long sheetId);

    // Check whether a student already has a record in a sheet
    boolean existsBySheet_IdAndStudentId(Long sheetId, Long studentId);
}
