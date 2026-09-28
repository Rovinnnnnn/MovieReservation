package com.rovinn.moviereservation.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table (name = "show_time")
public class ShowTimeData {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id" , nullable = false)
    private MovieData movie;
    @Column (nullable = false)
    private String hallName;
    @Column (nullable = false)
    private LocalDateTime timeStart;
    @Column (nullable = false)
    private Integer totalSeats;
    @Column
    private Integer availableSeats;
}
