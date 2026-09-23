package com.rovinn.moviereservation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table (name = "movies")
public class MovieData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false, unique = true)
    @NotBlank (message = "Title is required")
    private String title;
    @NotBlank (message = "Description is required")
    @Size(max = 400, message = "Description maximum is 400 Character long")
    private String description;
    @Enumerated(EnumType.STRING)
    private Category genre;
    @NotBlank (message = "Poster is required")
    private String poster;
    @ElementCollection
    @CollectionTable (name = "movie_showtime" , joinColumns = @JoinColumn(name = "movies_id"))
    @Column (name = "showtime")
    private List<String> showTimes = new ArrayList<>();
}
