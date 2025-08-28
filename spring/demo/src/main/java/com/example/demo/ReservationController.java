package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class ReservationController {

    private static final Logger logger = Logger.getLogger(ReservationController.class.getName());

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(
            @PathVariable("id") Long id
    ) {
        logger.info("Getting reservation with id " + id);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(reservationService.getReservationById(id));
        //return reservationService.getReservationById(id);
    }

    @GetMapping()
    public ResponseEntity<List<Reservation>> getAllReservations(){
        logger.info("Getting all reservations");
        return ResponseEntity.ok(reservationService.findAllReservations());
        //return reservationService.findAllReservations();
    }

    @PostMapping()
    public ResponseEntity<Reservation> createReservation(
            @RequestBody Reservation reservationToCreate
    ) {
        logger.info("Creating reservation " + reservationToCreate);
        return ResponseEntity.status(201)
                .header("test-header", "123")
                .body(reservationService.createReservation(reservationToCreate));
        //return reservationService.createReservation(reservationToCreate);
    }

}
