package com.example.timetableservice.services;

import com.example.timetableservice.entities.Session;
import com.example.timetableservice.entities.TimeSlot;
import com.example.timetableservice.entities.Timetable;
import com.example.timetableservice.repositories.SessionRepository;
import com.example.timetableservice.repositories.TimeSlotRepository;
import com.example.timetableservice.repositories.TimetableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final TimetableRepository timetableRepository;
    private final TimeSlotRepository timeSlotRepository;

    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    public Session findById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seance introuvable"));
    }

    public List<Session> findByTeacher(String teacherId) {
        return sessionRepository.findByTeacherId(teacherId);
    }

    public Session create(Session data) {
        return save(new Session(), data);
    }

    public Session update(Long id, Session data) {
        return save(findById(id), data);
    }

    public void delete(Long id) {
        sessionRepository.delete(findById(id));
    }

    private Session save(Session session, Session data) {
        Timetable timetable = findTimetable(data.getTimetable().getId());
        TimeSlot timeSlot = findTimeSlot(data.getTimeSlot().getId());

        // Pas deux seances sur le meme creneau pour la salle, l'enseignant ou le groupe
        boolean conflict = sessionRepository.findByTimeSlotId(timeSlot.getId()).stream()
                .filter(other -> !Objects.equals(other.getId(), session.getId()))
                .anyMatch(other -> other.getRoomId().equals(data.getRoomId())
                        || other.getTeacherId().equals(data.getTeacherId())
                        || other.getTimetable().getGroupName().equals(timetable.getGroupName()));
        if (conflict) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Salle, enseignant ou groupe deja occupe");
        }

        session.setCourseId(data.getCourseId());
        session.setRoomId(data.getRoomId());
        session.setTeacherId(data.getTeacherId());
        session.setSessionType(data.getSessionType());
        session.setTimetable(timetable);
        session.setTimeSlot(timeSlot);
        return sessionRepository.save(session);
    }

    private Timetable findTimetable(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "timetable.id est obligatoire");
        }
        return timetableRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Emploi du temps introuvable"));
    }

    private TimeSlot findTimeSlot(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "timeSlot.id est obligatoire");
        }
        return timeSlotRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Creneau introuvable"));
    }
}
