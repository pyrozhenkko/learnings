package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@RestController
@RequestMapping("/reservation")
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
        try {
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(reservationService.getReservationById(id));
            //return reservationService.getReservationById(id);
        }catch (NoSuchElementException e) {
            logger.info("Reservation not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(){
        logger.info("Getting all reservations");
        return ResponseEntity.ok(reservationService.findAllReservations());
        //return reservationService.findAllReservations();
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(
            @RequestBody Reservation reservationToCreate
    ) {
        logger.info("Creating reservation " + reservationToCreate);
        return ResponseEntity.status(201)
                .header("test-header", "123")
                .body(reservationService.createReservation(reservationToCreate));
        //return reservationService.createReservation(reservationToCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(
            @PathVariable("id") Long id,
            @RequestBody Reservation reservationToUpdate
    ){

        logger.info("Updating reservation " + reservationToUpdate);

        var updated = reservationService.updateReservation(id, reservationToUpdate);
        return ResponseEntity.ok((Reservation) updated);// якась дурня
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(
            @PathVariable("id") Long id
    ){
        try{
            logger.info("Deleting reservation ");
            reservationService.deleteReservation(id);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e){
            return ResponseEntity.status(404).build();
        }
    }
    @PostMapping("/{id}/approve")
    public ResponseEntity<Reservation> approveReservation(
          @PathVariable Long id
    ){
        logger.info("Approving reservation, id = " + id);
        var reservation = reservationService.approveReservation(id);
        return ResponseEntity.ok(reservation);
    }

}
