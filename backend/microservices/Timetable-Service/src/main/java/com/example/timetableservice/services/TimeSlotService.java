package com.example.timetableservice.services;

import com.example.timetableservice.entities.TimeSlot;
import com.example.timetableservice.repositories.SessionRepository;
import com.example.timetableservice.repositories.TimeSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final SessionRepository sessionRepository;

    public List<TimeSlot> findAll() {
        return timeSlotRepository.findAll();
    }

    public TimeSlot create(TimeSlot timeSlot) {
        if (!timeSlot.getEndTime().isAfter(timeSlot.getStartTime())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'heure de fin doit suivre l'heure de debut");
        }
        timeSlot.setId(null);
        return timeSlotRepository.save(timeSlot);
    }

    public void delete(Long id) {
        TimeSlot timeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Creneau introuvable"));
        if (sessionRepository.existsByTimeSlotId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ce creneau est utilise par des seances");
        }
        timeSlotRepository.delete(timeSlot);
    }
}
