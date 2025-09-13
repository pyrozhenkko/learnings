package com.example.demo.reservations.availability;

import org.springframework.lang.NonNull;

import java.time.LocalDate;

public record CheckAvaillabilityRequest (
        @NonNull
        Long roomId,
        @NonNull
        LocalDate startdate,
        @NonNull
        LocalDate enddate
) {
}
