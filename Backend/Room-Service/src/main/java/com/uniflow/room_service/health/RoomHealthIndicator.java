package com.uniflow.room_service.health;

import com.uniflow.room_service.repository.RoomRepository;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class RoomHealthIndicator implements HealthIndicator {

    private final RoomRepository roomRepository;

    public RoomHealthIndicator(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Health health() {
        try {
            long rooms = roomRepository.count();
            return Health.up().withDetail("rooms", rooms).build();
        } catch (Exception e) {
            return Health.down().withDetail("error", e.getMessage()).build();
        }
    }
}