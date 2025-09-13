package com.example.demo.reservations;

import com.example.demo.reservations.availability.ReservationAvailabilityService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ReservationService {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ReservationService.class);
    private final ReservationRepository repository;
    private final ReservationMapper mapper;

    private final ReservationAvailabilityService availabilityService;




    public ReservationService(ReservationRepository repository, ReservationMapper mapper, ReservationAvailabilityService availabilityService) {
        this.repository = repository;
        this.mapper = mapper;
        this.availabilityService = availabilityService;
    }

    public Reservation getReservationById(Long id) {

        repository.findAllByStatusIs(ReservationStatus.APPROVED);
        ReservationEntity reservationEntity =  repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Not found reservation by id = " + id
                ));
        return mapper.toDomain(reservationEntity);
    }

    public List<Reservation> findAllReservations() {
        List<ReservationEntity> allEntities = repository.findAll();
        List<Reservation> reservationList = allEntities.stream()
                .map(mapper::toDomain)
                .toList();

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

        var entityToSave =mapper.toEntity(reservationToCreate);

        var savedEntity = repository.save(entityToSave);
        return mapper.toDomain(savedEntity);
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
        var reservationToSave = mapper.toEntity(reservationToUpdate);
        reservationToSave.setId(reservationEntity.getId());
        reservationToSave.setStatus(ReservationStatus.PENDING);
        var updatedReservation = repository.save(reservationToSave);
        return mapper.toDomain(updatedReservation);
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
        var isAvailableToApprove = availabilityService.isReservationAvailable(
                reservationEntity.getRoomID(),
                reservationEntity.getStartDate(),
                reservationEntity.getEndDate()

        );
        if(!isAvailableToApprove) {
            throw new IllegalStateException("Cannot approve reservation because of conflict");
        }
        reservationEntity.setStatus(ReservationStatus.APPROVED);
        repository.save(reservationEntity);
        return mapper.toDomain(reservationEntity);
    }



    public List<Reservation> searchAllByFilter(ReservationSearchFilter filter) {

        int pageSize = filter.pageSize() != null
                ? filter.pageSize() : 10;

        int pageNumber = filter.pageNumber() != null
                ? filter.pageNumber() : 0;

        var pageable = Pageable
                .ofSize(pageSize)
                .withPage(pageNumber);

        List<ReservationEntity> allEntities = repository.searchAllByFilter(
                filter.roomId(),
                filter.userId(),
                pageable
        );

        return allEntities.stream()
                .map(mapper::toDomain)
                .toList();
    }
}
