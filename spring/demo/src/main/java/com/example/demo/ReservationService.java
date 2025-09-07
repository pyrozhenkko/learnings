package com.example.demo;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


@Service
public class ReservationService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ReservationService.class);
    private final ReservationRepository repository;

    private final static Logger logger = Logger.getLogger(ReservationService.class.getName());

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation getReservationById(Long id) {

        repository.findAllByStatusIs(ReservationStatus.APPROVED);
        ReservationEntity reservationEntity =  repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Not found reservation by id = " + id
                ));
        return toDomainReservation(reservationEntity);
    }

    public List<Reservation> findAllReservations() {
        List<ReservationEntity> allEntities = repository.findAll();
        List<Reservation> reservationList = allEntities.stream()
                .map(this::toDomainReservation).toList();

        return reservationList;
    }

    public Reservation createReservation(Reservation reservationToCreate) {
        if (reservationToCreate.id() != null) {
            throw new IllegalArgumentException("id is already set (should be empty)");
        }
        if (reservationToCreate.status() != null) {
            throw new IllegalArgumentException("status is already set (should be empty)");
        }
//        if(reservationToCreate.roomId().equals(10L)) {
//            throw new RuntimeException("test exception");
//        }

        var entityToSave = new ReservationEntity(
                null,
                reservationToCreate.userId(),
                reservationToCreate.roomId(),
                reservationToCreate.startDate(),
                reservationToCreate.endDate(),
                ReservationStatus.PENDING
        );

        var savedEntity = repository.save(entityToSave);
        return toDomainReservation(savedEntity);
    }


    public Object updateReservation(
            Long id,
            Reservation reservationToUpdate
    ) {

        var reservationEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found reservation with id = " + id));

        if (reservationEntity.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("reservation status is not PENDING, current status is " + reservationEntity.getStatus());
        }
        var reservationToSave = new ReservationEntity(
                reservationEntity.getId(),
                reservationToUpdate.userId(),
                reservationToUpdate.roomId(),
                reservationToUpdate.startDate(),
                reservationToUpdate.endDate(),
                ReservationStatus.PENDING
        );
        var updatedReservation = repository.save(reservationToSave);
        return toDomainReservation(updatedReservation);
    }

    @Transactional
    public void cancelReservation(Long id) {

        if(!repository.existsById(id)) {
            throw new EntityNotFoundException("Not found reservation with id = " + id);
        }
        repository.setStatus(id, ReservationStatus.CANCELLED);

        log.info("reservation with id = " + id +    "successfully cancelled");

    }

    public Reservation approveReservation(Long id) {
        var reservationEntity = repository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Not found reservation by id = " + id));
        if (reservationEntity.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("reservation status is not PENDING, cannot approve reservation");
        }
        var isConflict =  isReservationConflict(reservationEntity);
        if(isConflict) {
            throw new IllegalStateException("Cannot approve reservation because of conflict");
        }
        reservationEntity.setStatus(ReservationStatus.APPROVED);
        repository.save(reservationEntity);
        return toDomainReservation(reservationEntity);
    }

    private boolean isReservationConflict(ReservationEntity reservation) {
        var allReservations = repository.findAll();
        for (ReservationEntity existingReservation : allReservations) {
            if (reservation.getId().equals(existingReservation.getId())) {
                continue; // пропускаємо саму себе
            }
            if (!reservation.getRoomID().equals(existingReservation.getRoomID())) {
                continue; // інша кімната — нас не цікавить
            }
            if (!existingReservation.getStatus().equals(ReservationStatus.APPROVED)) {
                continue; // конфліктуємо лише з уже підтвердженими
            }

            // Перетин дат: (start < other.end) && (other.start < end)
            if (reservation.getStartDate().isBefore(existingReservation.getEndDate()) &&
                    existingReservation.getStartDate().isBefore(reservation.getEndDate())) {
                return true;
            }
        }
        return false;
    }
    private Reservation  toDomainReservation(
            ReservationEntity reservation
    ){
        return new Reservation(
                reservation.getId(),
                reservation.getUserId(),
                reservation.getRoomID(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getStatus()
        );
    }
}
