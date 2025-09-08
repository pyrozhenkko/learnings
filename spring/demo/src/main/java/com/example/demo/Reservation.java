package com.example.demo;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Null;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
