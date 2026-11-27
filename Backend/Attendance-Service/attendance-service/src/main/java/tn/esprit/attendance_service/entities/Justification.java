
package tn.esprit.attendance_service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "justification")
public class Justification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String reason;

    @NotNull
    @Column(nullable = false)
    private LocalDate submittedDate;

    @NotBlank
    @Column(nullable = false)
    private String status;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "record_id", nullable = false, unique = true)
    private AttendanceRecord record;

    // Constructor
    public Justification() {
    }

    public Justification(String reason, LocalDate submittedDate,
                         String status, AttendanceRecord record) {
        this.reason = reason;
        this.submittedDate = submittedDate;
        this.status = status;
        this.record = record;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDate getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDate submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AttendanceRecord getRecord() {
        return record;
    }

    public void setRecord(AttendanceRecord record) {
        this.record = record;
    }
}
