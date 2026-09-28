    package com.rovinn.moviereservation.Service;

    import com.rovinn.moviereservation.Exception.AccessDeniedException;
    import com.rovinn.moviereservation.Exception.DuplicationResourceException;
    import com.rovinn.moviereservation.Exception.ResourceNotFoundException;
    import com.rovinn.moviereservation.Repository.ReservationRepository;
    import com.rovinn.moviereservation.Repository.ShowTimeRepository;
    import com.rovinn.moviereservation.Repository.UserRepository;
    import com.rovinn.moviereservation.model.*;
    import jakarta.transaction.Transactional;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class ReservationServiceImplement implements ReservationServiceInterface {
        private final ReservationRepository reservationRepository;
        private final UserRepository userRepository;
        private final ShowTimeRepository showTimesRepository;
        public ReservationServiceImplement(ReservationRepository reservationRepository, UserRepository userRepository, ShowTimeRepository  showTimesRepository) {
            this.reservationRepository = reservationRepository;
            this.userRepository = userRepository;
            this.showTimesRepository = showTimesRepository;
        }
        @Override
        @Transactional
        public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO, String email) {

            UserData user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found with email : " + email));
            ShowTimeData showTime = showTimesRepository.findById(reservationRequestDTO.getShowTimeId()).orElseThrow(() -> new ResourceNotFoundException("Show Time not found"));
            if(reservationRepository.existsByShowTimeIdAndSeatNumber(showTime.getId(), reservationRequestDTO.getSeatNumber())){
                throw new DuplicationResourceException("SeatNumber is Already Taken!");
            }
            if(showTime.getAvailableSeats() <= 0){
                throw new RuntimeException("No seat available Available!");
            }
            ReservationData reservation = new  ReservationData();
            reservation.setUser(user);
            reservation.setShowTime(showTime);
            reservation.setSeatNumber(reservationRequestDTO.getSeatNumber());
            reservation.setReservationTime(reservationRequestDTO.getReservationTime());
            reservation.setStatus(Status.PENDING);
            reservation.setPayment(reservationRequestDTO.getPayment());
            showTime.setAvailableSeats(showTime.getAvailableSeats() - 1);
            ReservationData savedReservation = reservationRepository.save(reservation);

            return mapToReservationResponseDTO(savedReservation);
        }
        @Override
        public ReservationResponseDTO getReservationById(Long id,String userEmail,String userRole) {
            ReservationData find = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id : " + id));

            boolean isOwner = find.getUser().getEmail().equals(userEmail);
            boolean isAdmin = "ROLE_ADMIN".equals(userRole);

            if (!isOwner && !isAdmin) {
                throw new AccessDeniedException("You are not allowed to access this resource");
            }

            return mapToReservationResponseDTO(find);
        }
        @Override
        public List<ReservationResponseDTO> getReservationsByUser(String userEmail) {
            UserData user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User not found with email : " + userEmail));
            List<ReservationData> reservation = reservationRepository.findByUser(user);
            return reservation.stream().map(this::mapToReservationResponseDTO).toList();
        }
        @Override
        public List<ReservationResponseDTO> getAllReservations() {
            return reservationRepository.findAll().stream().map(this::mapToReservationResponseDTO).toList();
        }
        @Override
        @Transactional
        public void cancelReservationById(Long id, String email) {
          UserData user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found with email : " + email));
          ReservationData reservation = reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id : " + id));
          if (!reservation.getUser().getId().equals(user.getId())) {
              throw new AccessDeniedException("You are not allowed to cancel this reservation");
          }
          if (reservation.getStatus() != Status.PENDING){
              throw new IllegalStateException("Cannot cancel reservation that is already " +  reservation.getStatus());
          }
          reservation.setStatus(Status.CANCELLED);
          ShowTimeData showtime = new ShowTimeData();
          showtime.setAvailableSeats(showtime.getAvailableSeats()+1);
        }
        private ReservationResponseDTO mapToReservationResponseDTO(ReservationData reservationData) {
            ReservationResponseDTO reservationResponseDTO = new ReservationResponseDTO();
            reservationResponseDTO.setId(reservationData.getId());
            reservationResponseDTO.setUserId(reservationData.getUser().getId());
            reservationResponseDTO.setShowTimeId(reservationData.getShowTime().getId());
            reservationResponseDTO.setSeatNumber(reservationData.getSeatNumber());
            reservationResponseDTO.setReservationTime(reservationData.getReservationTime());
            reservationResponseDTO.setStatus(reservationData.getStatus());
            reservationResponseDTO.setPayment(reservationData.getPayment());
            return reservationResponseDTO;
        }
    }
