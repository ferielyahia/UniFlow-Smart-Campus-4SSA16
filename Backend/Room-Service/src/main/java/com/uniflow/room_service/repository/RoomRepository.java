package com.uniflow.room_service.repository;

import com.uniflow.room_service.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByCode(String code);
}