package tn.esprit.attendance_service.repositories;
import tn.esprit.attendance_service.entities.AttendanceSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface AttendanceSheetRepository
        extends JpaRepository<AttendanceSheet, Long> {

    // Find an attendance sheet by its timetable session ID
    Optional<AttendanceSheet> findBySessionId(Long sessionId);

    // Check whether an attendance sheet already exists for a session
    boolean existsBySessionId(Long sessionId);
}
