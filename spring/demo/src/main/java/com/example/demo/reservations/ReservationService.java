package com.example.demo.reservations;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
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
//        if (reservationToCreate.id() != null) {
//            throw new IllegalArgumentException("id is already set (should be empty)");
//        }// ми це вже перевіряємо через анотація @Null
        if (reservationToCreate.status() != null) {
            throw new IllegalArgumentException("status is already set (should be empty)");
        }

        if(!reservationToCreate.endDate().isAfter(reservationToCreate.startDate())) {
            throw new IllegalArgumentException("endDate is after startDate");
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
        if(!reservationToUpdate.endDate().isAfter(reservationToUpdate.startDate())) {
            throw new IllegalArgumentException("endDate is after startDate");
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
        var reservationEntity = repository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Not found reservation with id = " + id));
        if(reservationEntity.getStatus().equals(ReservationStatus.APPROVED)) {
            throw new IllegalStateException("Cannot cancel approved reservation, id = " + id );
        }
        if(reservationEntity.getStatus().equals(ReservationStatus.CANCELLED)) {
            throw new IllegalStateException("Reservation is already cancelled, id = " + id );
        }
        repository.setStatus(id, ReservationStatus.CANCELLED);

        log.info("reservation with id = " + id + "successfully cancelled");

    }

    public Reservation approveReservation(Long id) {
        var reservationEntity = repository.findById(id)
                .orElseThrow( () -> new EntityNotFoundException("Not found reservation by id = " + id));
        if (reservationEntity.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("reservation status is not PENDING, cannot approve reservation");
        }
        var isConflict =  isReservationConflict(
                reservationEntity.getRoomID(),
                reservationEntity.getStartDate(),
                reservationEntity.getEndDate()

        );
        if(isConflict) {
            throw new IllegalStateException("Cannot approve reservation because of conflict");
        }
        reservationEntity.setStatus(ReservationStatus.APPROVED);
        repository.save(reservationEntity);
        return toDomainReservation(reservationEntity);
    }

    private boolean isReservationConflict(
            Long roomId,
            LocalDate startDate,
            LocalDate endDate
    ) {
        List<Long> conflictIds = repository.findConflictReservationIds(
                roomId,
                startDate,
                endDate,
                ReservationStatus.APPROVED
        );
        if(conflictIds.isEmpty()) {
            return false;
        }
        log.info("Conflicting with ids = {}" + conflictIds);
        return true;
    }

}
