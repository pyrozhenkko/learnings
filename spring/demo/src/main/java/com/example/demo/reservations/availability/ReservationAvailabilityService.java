package com.example.demo.reservations.availability;

import com.example.demo.reservations.ReservationRepository;
import com.example.demo.reservations.ReservationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationAvailabilityService {
    private static final Logger log = LoggerFactory.getLogger(ReservationAvailabilityController.class);

    private final ReservationRepository repository;
    public ReservationAvailabilityService(ReservationRepository repository) {
        this.repository = repository;
    }
    public boolean isReservationAvailable(
            Long roomId,
            LocalDate startDate,
            LocalDate endDate
    ) {

        if(!endDate.isAfter(startDate)) {
        throw new IllegalArgumentException("endDate is after startDate");
    }
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
