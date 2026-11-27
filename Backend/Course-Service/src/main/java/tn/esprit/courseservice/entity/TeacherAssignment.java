package tn.esprit.courseservice.entity;

import jakarta.persistence.*;

@Entity
public class TeacherAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Identifiant de l'enseignant : simple valeur, pas de clé étrangère
    // (les enseignants ne sont pas dans la base de ce service)
    @Column(nullable = false)
    private Long teacherId;

    private String role;

    @ManyToOne(optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    public TeacherAssignment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Course getCourse() { return course; }
    public void setCourse(Course course) { this.course = course; }
}
