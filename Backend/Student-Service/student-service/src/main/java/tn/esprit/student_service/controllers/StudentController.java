
package tn.esprit.student_service.controllers;

import tn.esprit.student_service.entities.Student;
import tn.esprit.student_service.services.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Ajouter un étudiant
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return new ResponseEntity<>(
                studentService.addStudent(student),
                HttpStatus.CREATED
        );
    }

    // Récupérer tous les étudiants
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Récupérer un étudiant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Modifier un étudiant
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        if (studentService.getStudentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                studentService.updateStudent(id, student)
        );
    }

    // Supprimer un étudiant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {

        if (studentService.getStudentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
