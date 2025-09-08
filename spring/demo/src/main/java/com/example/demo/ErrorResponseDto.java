package com.example.demo;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        String message,
        String detailMessage, //errorMessage
        LocalDateTime errorTime
) {

}
