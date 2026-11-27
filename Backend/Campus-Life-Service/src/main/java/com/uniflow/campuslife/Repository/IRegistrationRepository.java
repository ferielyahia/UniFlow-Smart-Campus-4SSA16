package com.uniflow.campuslife.Repository;

import com.uniflow.campuslife.Domain.Registration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRegistrationRepository extends JpaRepository<Registration,Long> {
}
