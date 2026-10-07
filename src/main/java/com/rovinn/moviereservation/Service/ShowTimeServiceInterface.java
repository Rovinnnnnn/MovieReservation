package com.rovinn.moviereservation.Service;

import com.rovinn.moviereservation.model.ShowTimeRequestDTO;
import com.rovinn.moviereservation.model.ShowTimeResponseDTO;

import java.util.List;

public interface ShowTimeServiceInterface {
    ShowTimeResponseDTO createShowTime(ShowTimeRequestDTO  requestDTO);
    List<ShowTimeResponseDTO> getShowTimesByMovieId(Long movieId);
    void deleteShowTime(Long id);
    List<String> getTakenSeats(Long showTimeId);
}
