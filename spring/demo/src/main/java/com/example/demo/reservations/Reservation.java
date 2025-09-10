package com.example.demo.reservations;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Null;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

public record Reservation(
        @Null
        Long id,
        @NonNull
        Long userId,
        @NonNull
        Long roomId,
        @FutureOrPresent
        @NonNull
        LocalDate startDate,
        @FutureOrPresent
        @NonNull
        LocalDate endDate,

        ReservationStatus status
) {
}
