package com.rovinn.moviereservation.Service;

import com.rovinn.moviereservation.model.ReservationRequestDTO;
import com.rovinn.moviereservation.model.ReservationResponseDTO;


import java.util.List;

public interface ReservationServiceInterface {
   ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO, String email);
   ReservationResponseDTO getReservationById(Long id,String userEmail,String userRole);
   List<ReservationResponseDTO> getReservationsByUser(String userEmail);
   List<ReservationResponseDTO> getAllReservations();
   void cancelReservationById(Long id,String email);
}
