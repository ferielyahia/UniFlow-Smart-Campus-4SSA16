package tn.esprit.attendance_service.repositories;

import tn.esprit.attendance_service.entities.Justification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface JustificationRepository
        extends JpaRepository<Justification, Long> {

    // Find a justification associated with an attendance record
    Optional<Justification> findByRecord_Id(Long recordId);

    // Check whether a justification already exists for a record
    boolean existsByRecord_Id(Long recordId);

    // Find justifications by validation status
    List<Justification> findByStatus(String status);
}
