package tn.esprit.courseservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.courseservice.entity.Subject;
import tn.esprit.courseservice.repository.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @GetMapping
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Subject> create(@RequestBody Subject subject) {
        if (subject.getName() == null || subject.getCode() == null) {
            return ResponseEntity.badRequest().build();
        }
        subject.setId(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(subjectRepository.save(subject));
    }
}
