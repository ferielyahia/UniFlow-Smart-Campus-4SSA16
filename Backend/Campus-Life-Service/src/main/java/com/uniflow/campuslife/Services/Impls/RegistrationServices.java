package com.uniflow.campuslife.Services.Impls;

import com.uniflow.campuslife.Domain.Event;
import com.uniflow.campuslife.Domain.Registration;
import com.uniflow.campuslife.Repository.IEventRepository;
import com.uniflow.campuslife.Repository.IRegistrationRepository;
import com.uniflow.campuslife.Services.IRegistrationServices;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class RegistrationServices implements IRegistrationServices {
    private final IRegistrationRepository iRegistrationRepo;
    private final IEventRepository iEventRepo;


    @Override
    public Registration addRegistration(Registration registration, Long idEvent) {
        Event event = iEventRepo.findById(idEvent).orElseThrow(() -> new EntityNotFoundException("event not found"));
        registration.setEvent(event);
        return iRegistrationRepo.save(registration);
    }

    @Override
    public Registration getRegistration(Long id) {
        return iRegistrationRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("registration not found"));
    }

    @Override
    public Registration updateRegistration(Long id, Registration registration) {
        registration.setId(id);
        registration.setEvent(getRegistration(id).getEvent());
        return iRegistrationRepo.save(registration);
    }

    @Override
    public void deleteRegistration(Long id) {
        iRegistrationRepo.deleteById(id);
    }

    @Override
    public List<Registration> getAll() {
        return iRegistrationRepo.findAll();
    }
}
