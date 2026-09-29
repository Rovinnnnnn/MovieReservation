package com.rovinn.moviereservation.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ShowTimeRequestDTO {

    @NotNull (message = "Movie id is required")
    private Long movieId;
    @NotBlank (message = "Hall name is required")
    private String hallName;
    @NotNull (message = "Time start is required")
    private LocalDateTime timeStart;
    @NotNull (message = "Total seat is required")
    private Integer totalSeats;
    @NotNull (message = "Available seat is required")
    private Integer availableSeats;
}
