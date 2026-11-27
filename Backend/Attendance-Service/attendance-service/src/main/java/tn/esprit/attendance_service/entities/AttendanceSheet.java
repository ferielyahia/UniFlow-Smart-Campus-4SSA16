package tn.esprit.attendance_service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "attendance_sheet")
public class AttendanceSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long sessionId;

    @NotBlank
    @Column(nullable = false)
    private String teacherId;

    @NotNull
    @Column(nullable = false)
    private LocalDate sheetDate;

    @NotBlank
    @Column(nullable = false)
    private String status;

    // One attendance sheet contains multiple attendance records
    @OneToMany(
            mappedBy = "sheet",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AttendanceRecord> records = new ArrayList<>();

    // Constructors
    public AttendanceSheet() {
    }

    public AttendanceSheet(Long sessionId, String teacherId,
                           LocalDate sheetDate, String status) {
        this.sessionId = sessionId;
        this.teacherId = teacherId;
        this.sheetDate = sheetDate;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public LocalDate getSheetDate() {
        return sheetDate;
    }

    public void setSheetDate(LocalDate sheetDate) {
        this.sheetDate = sheetDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AttendanceRecord> getRecords() {
        return records;
    }

    public void setRecords(List<AttendanceRecord> records) {
        this.records = records;
    }
}
