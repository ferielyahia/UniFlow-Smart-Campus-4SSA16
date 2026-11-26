package tn.esprit.student_service.services;

import tn.esprit.student_service.entities.AcademicYear;
import tn.esprit.student_service.repositories.AcademicYearRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicYearService {

    private final AcademicYearRepository academicYearRepository;

    public AcademicYearService(AcademicYearRepository academicYearRepository) {
        this.academicYearRepository = academicYearRepository;
    }

    public AcademicYear addAcademicYear(AcademicYear academicYear) {
        return academicYearRepository.save(academicYear);
    }

    public List<AcademicYear> getAllAcademicYears() {
        return academicYearRepository.findAll();
    }

    public Optional<AcademicYear> getAcademicYearById(Long id) {
        return academicYearRepository.findById(id);
    }

    public AcademicYear updateAcademicYear(Long id, AcademicYear academicYear) {
        academicYear.setId(id);
        return academicYearRepository.save(academicYear);
    }

    public void deleteAcademicYear(Long id) {
        academicYearRepository.deleteById(id);
    }
}
