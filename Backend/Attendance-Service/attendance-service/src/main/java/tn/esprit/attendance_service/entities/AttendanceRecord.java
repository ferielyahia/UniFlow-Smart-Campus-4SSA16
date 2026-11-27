package tn.esprit.attendance_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "attendance_record")
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private Long studentId;

    @NotBlank
    @Column(nullable = false)
    private String status;

    @Column
    private String remark;

    // The sheet is assigned by the service, not by the JSON request
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sheet_id", nullable = false)
    @JsonIgnore
    private AttendanceSheet sheet;

    // Constructor
    public AttendanceRecord() {
    }

    public AttendanceRecord(Long studentId, String status,
                            String remark, AttendanceSheet sheet) {
        this.studentId = studentId;
        this.status = status;
        this.remark = remark;
        this.sheet = sheet;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public AttendanceSheet getSheet() {
        return sheet;
    }

    public void setSheet(AttendanceSheet sheet) {
        this.sheet = sheet;
    }
}
