package com.rovinn.moviereservation.model;

import lombok.Data;

import java.util.List;

@Data
public class MovieResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Category genre;
    private String poster;
    private List<String> showTimes;
}
