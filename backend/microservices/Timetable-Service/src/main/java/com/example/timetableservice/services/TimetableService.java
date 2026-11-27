package com.example.timetableservice.services;

import com.example.timetableservice.entities.Timetable;
import com.example.timetableservice.repositories.SessionRepository;
import com.example.timetableservice.repositories.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final SessionRepository sessionRepository;

    public List<Timetable> findAll() {
        return timetableRepository.findAll();
    }

    public Timetable findById(Long id) {
        return timetableRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Emploi du temps introuvable"));
    }

    public Timetable create(Timetable timetable) {
        timetable.setId(null);
        return timetableRepository.save(timetable);
    }

    public Timetable update(Long id, Timetable data) {
        Timetable timetable = findById(id);
        timetable.setLabel(data.getLabel());
        timetable.setGroupName(data.getGroupName());
        timetable.setSemester(data.getSemester());
        timetable.setStatus(data.getStatus());
        return timetableRepository.save(timetable);
    }

    public void delete(Long id) {
        Timetable timetable = findById(id);
        if (sessionRepository.existsByTimetableId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cet emploi du temps contient des seances");
        }
        timetableRepository.delete(timetable);
    }
}
