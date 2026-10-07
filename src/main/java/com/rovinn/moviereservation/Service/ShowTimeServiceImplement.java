package com.rovinn.moviereservation.Service;

import com.rovinn.moviereservation.Exception.ResourceNotFoundException;
import com.rovinn.moviereservation.Repository.MovieRepository;
import com.rovinn.moviereservation.Repository.ReservationRepository;
import com.rovinn.moviereservation.Repository.ShowTimeRepository;
import com.rovinn.moviereservation.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowTimeServiceImplement implements ShowTimeServiceInterface {
    private final ShowTimeRepository showTimeRepository;
    private final MovieRepository movieRepository;
    private final ReservationRepository reservationRepository;
    public ShowTimeServiceImplement (ShowTimeRepository showTimeRepository, MovieRepository movieRepository,ReservationRepository reservationRepository) {
        this.showTimeRepository=showTimeRepository;
        this.movieRepository=movieRepository;
        this.reservationRepository=reservationRepository;
    }
    @Override
    public ShowTimeResponseDTO createShowTime(ShowTimeRequestDTO requestDTO) {
        MovieData movie = movieRepository.findById(requestDTO.getMovieId()).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        ShowTimeData create = new ShowTimeData();
        create.setMovie(movie);
        create.setHallName(requestDTO.getHallName());
        create.setTimeStart(requestDTO.getTimeStart());
        create.setAvailableSeats(requestDTO.getTotalSeats());
        create.setTotalSeats(requestDTO.getTotalSeats());

        ShowTimeData saved = showTimeRepository.save(create);
        return mapToShowTimeResponse(saved);
    }

    @Override
    public List<ShowTimeResponseDTO> getShowTimesByMovieId(Long movieId) {
        return showTimeRepository.findByMovieId(movieId).stream().map(this::mapToShowTimeResponse).toList();
    }

    @Override
    public void deleteShowTime(Long id) {
        showTimeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Show time not found"));
        showTimeRepository.deleteById(id);
    }
    @Override
    public List<String> getTakenSeats(Long showTimeId) {
        return reservationRepository.findByShowTimeIdAndStatusNot(showTimeId, Status.CANCELLED)
                .stream().map(ReservationData::getSeatNumber).toList();
    }

    private ShowTimeResponseDTO mapToShowTimeResponse(ShowTimeData data){
        ShowTimeResponseDTO showTime = new ShowTimeResponseDTO();
        showTime.setId(data.getId());
        showTime.setMovieId(data.getMovie().getId());
        showTime.setHallName(data.getHallName());
        showTime.setTimeStart(data.getTimeStart());
        showTime.setAvailableSeats(data.getAvailableSeats());
        showTime.setTotalSeats(data.getTotalSeats());
        return showTime;
    }
}
