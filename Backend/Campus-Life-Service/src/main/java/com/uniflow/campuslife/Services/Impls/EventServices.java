package com.uniflow.campuslife.Services.Impls;

import com.uniflow.campuslife.Domain.Event;
import com.uniflow.campuslife.Repository.IEventRepository;
import com.uniflow.campuslife.Services.IEventServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class EventServices implements IEventServices {
    private final IEventRepository iEventRepo;
    @Override
    public Event addEvent(Event event) {
        return iEventRepo.save(event);
    }

    @Override
    public Event getEvent(Long id) {
        return iEventRepo.findById(id).orElseThrow(()->new EntityNotFoundException("Event not found"));
    }

    @Override
    public Event updateEvent(Long id, Event event) {
        event.setId(id);
        return iEventRepo.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        iEventRepo.deleteById(id);
    }

    @Override
    public List<Event> getAll() {
        return iEventRepo.findAll();
    }
}
