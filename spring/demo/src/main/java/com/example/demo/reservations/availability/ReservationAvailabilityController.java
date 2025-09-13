package com.example.demo.reservations.availability;

import com.example.demo.reservations.Reservation;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation/availability")
public class ReservationAvailabilityController {
    private static final Logger log = LoggerFactory.getLogger(ReservationAvailabilityController.class);
    private final ReservationAvailabilityService service;
    public ReservationAvailabilityController(ReservationAvailabilityService service) {
        this.service = service;
    }

    @PostMapping("/check")
    public ResponseEntity<CheckAvailabilityResponse> checkAvailability(
            @Valid CheckAvaillabilityRequest request) {
        log.info("Check availability request: {}", request);
        boolean isAvailable = service.isReservationAvailable(
                request.roomId(),
                request.startdate(),
                request.enddate()
        );
        var message = isAvailable?"Room available to reservation":"Room not available to reservation";
        var status = isAvailable? AvailabilityStatus.AVAILABLE : AvailabilityStatus.RESERVED;
        return ResponseEntity.status(200)
                .body(new CheckAvailabilityResponse(message, status));
    }
}

