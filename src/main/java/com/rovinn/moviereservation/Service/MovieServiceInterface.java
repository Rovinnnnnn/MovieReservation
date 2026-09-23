package com.rovinn.moviereservation.Service;

import com.rovinn.moviereservation.model.MovieRequestDTO;
import com.rovinn.moviereservation.model.MovieResponseDTO;

import java.util.List;
   public interface MovieServiceInterface {
    MovieResponseDTO addMovie(MovieRequestDTO movieRequestDTO);
    MovieResponseDTO getMovieById(Long id);
    List<MovieResponseDTO> getAllMovies();
    MovieResponseDTO updateMovieById(Long id,MovieRequestDTO movieRequestDTO);
    void deleteMovie(Long id);
}
