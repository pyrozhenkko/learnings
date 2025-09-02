package com.example.demo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


@Service
public class ReservationService {
    private final Map<Long, Reservation> reservationMap;
    private final AtomicLong idCounter;
    private final ReservationRepository repository;

    private final static Logger logger = Logger.getLogger(ReservationService.class.getName());

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
        reservationMap = new HashMap<>();
        idCounter = new AtomicLong();
    }

    public Reservation getReservationById(Long id) {
        if(!reservationMap.containsKey(id)) {
            throw new NoSuchElementException("Not found reservation with id " + id);
        }
        return reservationMap.get(id);
    }

    public List<Reservation> findAllReservations() {
        List<ReservationEntity> allEntities = repository.findAll();
        List<Reservation> reservationList = allEntities.stream()
                .map(it ->
                    new Reservation(
                            it.getId(),
                            it.getUserId(),
                            it.getRoomID(),
                            it.getStartDate(),
                            it.getEndDate(),
                            it.getStatus()
                    )
                ).toList();

        return reservationList;
    }

    public Reservation createReservation(Reservation reservationToCreate) {
        if (reservationToCreate.id() != null) {
            throw new NoSuchElementException("id is already set(should be empty)");
        }
        if (reservationToCreate.status() != null) {
            throw new NoSuchElementException("status is already set(should be empty)");
        }
        var newReservation = new Reservation(
                idCounter.incrementAndGet(),
                reservationToCreate.userId(),
                reservationToCreate.roomId(),
                reservationToCreate.startDate(),
                reservationToCreate.endDate(),
                ReservationStatus.PENDING
        );
        reservationMap.put(newReservation.id(), newReservation);
        return newReservation;
    }

    public Object updateReservation(
            Long id,
            Reservation reservationToUpdate
    ) {
        if(!reservationMap.containsKey(id)) {
            throw new NoSuchElementException("Not found reservation with id = " + id);
        }
        var reservation = reservationMap.get(id);
        if (reservation.status() != ReservationStatus.PENDING) {
            throw new IllegalStateException("reservation status is not PENDING, current status is " + reservation.status());
        }
        var updatedReservation = new Reservation(
                reservation.id(),
                reservationToUpdate.userId(),
                reservationToUpdate.roomId(),
                reservationToUpdate.startDate(),
                reservationToUpdate.endDate(),
                ReservationStatus.PENDING
        );
        reservationMap.put(reservation.id(), updatedReservation);
        return updatedReservation;
    }

    public void deleteReservation(Long id) {
        if(!reservationMap.containsKey(id)) {
            logger.info("Not found reservation with id = " + id);
            throw new NoSuchElementException("Not found reservation with id " + id);
        }
        reservationMap.remove(id);

    }

    public Reservation approveReservation(Long id) {
        if(!reservationMap.containsKey(id)) {
            throw new NoSuchElementException("Not found reservation with id " + id);
        }
        var reservation = reservationMap.get(id);
        if (reservation.status() != ReservationStatus.PENDING) {
            throw new IllegalStateException("reservation status is not PENDING, cannot approve reservation");
        }
        var isConflict =  isReservationConflict(reservation);
        if(isConflict) {
            throw new IllegalStateException("Cannot approve reservation because of conflict");
        }
        var  approvedReservation = new Reservation(
                reservation.id(),
                reservation.userId(),
                reservation.roomId(),
                reservation.startDate(),
                reservation.endDate(),
                ReservationStatus.APPROVED
        );
        reservationMap.put(reservation.id(), approvedReservation);
        return approvedReservation;
    }

    private boolean isReservationConflict(Reservation reservation) {
        for (Reservation existing : reservationMap.values()) {
            if (existing.id().equals(reservation.id())) {
                continue; // пропускаємо саму себе
            }
            if (!Objects.equals(existing.roomId(), reservation.roomId())) {
                continue; // інша кімната — нас не цікавить
            }
            if (existing.status() != ReservationStatus.APPROVED) {
                continue; // конфліктуємо лише з уже підтвердженими
            }

            // Перетин дат: (start < other.end) && (other.start < end)
            if (reservation.startDate().isBefore(existing.endDate()) &&
                    existing.startDate().isBefore(reservation.endDate())) {
                return true;
            }
        }
        return false;
    }
}
