package com.rovinn.moviereservation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table (name = "reservations")
public class ReservationData {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "user_id" ,nullable = false)
    private UserData user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_time_id", nullable = false)
    private ShowTimeData showTime;
    @NotBlank(message = "Seat Number is required")
    @Column(nullable = false)
    private String seatNumber;
    @NotNull(message = "Reservation is required")
    @Column(nullable = false)
    private LocalDateTime reservationTime;
    @Enumerated (EnumType.STRING)
    private Status status = Status.CONFIRMED;
    @NotNull (message = "Payment is required")
    @Column(nullable = false)
    private Double payment;
}
