package com.uniflow.campuslife.Services;

import com.uniflow.campuslife.Domain.Registration;

import java.util.List;

public interface IRegistrationServices {
    Registration addRegistration(Registration registration, Long idEvent);
    Registration getRegistration(Long id);
    Registration updateRegistration(Long id, Registration registration);
    void deleteRegistration(Long id);
    List<Registration> getAll();
}
