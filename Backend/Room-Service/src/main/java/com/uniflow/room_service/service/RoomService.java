package com.uniflow.room_service.service;

import com.uniflow.room_service.entity.Room;
import com.uniflow.room_service.exception.ConflictException;
import com.uniflow.room_service.exception.ResourceNotFoundException;
import com.uniflow.room_service.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() { return roomRepository.findAll(); }

    public Room findById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salle introuvable : " + id));
    }

    public Room create(Room room) {
        if (roomRepository.existsByCode(room.getCode())) {
            throw new ConflictException("Code déjà utilisé : " + room.getCode());
        }
        room.setId(null);
        return roomRepository.save(room);
    }

    public Room update(Long id, Room data) {
        Room room = findById(id);
        if (!room.getCode().equals(data.getCode()) && roomRepository.existsByCode(data.getCode())) {
            throw new ConflictException("Code déjà utilisé : " + data.getCode());
        }
        room.setCode(data.getCode());
        room.setName(data.getName());
        room.setBuilding(data.getBuilding());
        room.setFloor(data.getFloor());
        room.setCapacity(data.getCapacity());
        room.setType(data.getType());
        if (data.getStatus() != null) room.setStatus(data.getStatus());
        return roomRepository.save(room);
    }

    public void delete(Long id) { roomRepository.delete(findById(id)); }
}