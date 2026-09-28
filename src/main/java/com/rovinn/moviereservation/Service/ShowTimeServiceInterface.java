package com.rovinn.moviereservation.Service;

import com.rovinn.moviereservation.model.ShowTimeRequestDTO;
import com.rovinn.moviereservation.model.ShowTimeResponseDTO;

import java.util.List;

public interface ShowTimeServiceInterface {
    ShowTimeResponseDTO createShowTime(ShowTimeRequestDTO  requestDTO);
    List<ShowTimeResponseDTO>  getAllShowTime ();
    void deleteShowTime(Long id);
}
