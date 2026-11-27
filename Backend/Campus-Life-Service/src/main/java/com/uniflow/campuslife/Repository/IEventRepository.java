package com.uniflow.campuslife.Repository;

import com.uniflow.campuslife.Domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventRepository extends JpaRepository <Event,Long> {
}
