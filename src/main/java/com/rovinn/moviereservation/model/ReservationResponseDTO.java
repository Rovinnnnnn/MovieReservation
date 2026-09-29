package com.rovinn.moviereservation.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReservationResponseDTO {
    private Long id;
    private Long userId;
    private Long showTimeId;
    private String seatNumber;
    private LocalDateTime reservationTime;
    private Status status;
    private Double payment;
}
