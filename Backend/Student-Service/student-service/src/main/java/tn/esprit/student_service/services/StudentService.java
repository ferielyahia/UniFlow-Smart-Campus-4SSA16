
package tn.esprit.student_service.services;

import tn.esprit.student_service.entities.Student;
import org.springframework.stereotype.Service;
import tn.esprit.student_service.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Ajouter un étudiant
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Récupérer tous les étudiants
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Récupérer un étudiant par ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Modifier un étudiant
    public Student updateStudent(Long id, Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    // Supprimer un étudiant
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
