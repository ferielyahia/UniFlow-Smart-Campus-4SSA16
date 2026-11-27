package com.uniflow.room_service.service;

import com.uniflow.room_service.dto.ReservationRequest;
import com.uniflow.room_service.entity.Reservation;
import com.uniflow.room_service.entity.ReservationStatus;
import com.uniflow.room_service.entity.Room;
import com.uniflow.room_service.exception.ConflictException;
import com.uniflow.room_service.exception.ResourceNotFoundException;
import com.uniflow.room_service.repository.ReservationRepository;
import com.uniflow.room_service.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public List<Reservation> findAll() { return reservationRepository.findAll(); }

    @Transactional
    public Reservation create(ReservationRequest req) {
        if (!req.endTime().isAfter(req.startTime())) {
            throw new IllegalArgumentException("endTime doit être après startTime");
        }
        Room room = roomRepository.findById(req.roomId())
                .orElseThrow(() -> new ResourceNotFoundException("Salle introuvable : " + req.roomId()));
        if (reservationRepository.existsOverlap(room.getId(), req.reservationDate(),
                req.startTime(), req.endTime())) {
            throw new ConflictException("Salle déjà réservée sur ce créneau");
        }
        Reservation r = new Reservation();
        r.setRoom(room);
        r.setReservedBy(req.reservedBy());
        r.setPurpose(req.purpose());
        r.setReservationDate(req.reservationDate());
        r.setStartTime(req.startTime());
        r.setEndTime(req.endTime());
        return reservationRepository.save(r);
    }

    @Transactional
    public void cancel(Long id) {
        Reservation r = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Réservation introuvable : " + id));
        r.setStatus(ReservationStatus.CANCELLED);
    }
}