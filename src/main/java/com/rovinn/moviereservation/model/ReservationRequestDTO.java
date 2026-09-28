package com.rovinn.moviereservation.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationRequestDTO {

    @NotNull(message = "User is required")
    private Long userId;

    @NotNull(message = "Show Time is required")
    private Long ShowTimeId;

    @NotBlank(message = "Seat Number is required")
    private String seatNumber;

    @NotNull(message = "Reservation time is required")
    private LocalDateTime reservationTime;

    @NotNull(message = "Payment is required")
    private Double payment;

}