package com.uniflow.room_service.repository;

import com.uniflow.room_service.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
        select count(r) > 0 from Reservation r
        where r.room.id = :roomId
          and r.reservationDate = :date
          and r.status = com.uniflow.room_service.entity.ReservationStatus.ACTIVE
          and r.startTime < :end and r.endTime > :start
        """)
    boolean existsOverlap(@Param("roomId") Long roomId,
                          @Param("date") LocalDate date,
                          @Param("start") LocalTime start,
                          @Param("end") LocalTime end);
}