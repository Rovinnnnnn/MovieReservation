package com.rovinn.moviereservation.Controller;

import com.rovinn.moviereservation.Service.ReservationServiceInterface;
import com.rovinn.moviereservation.model.ReservationRequestDTO;
import com.rovinn.moviereservation.model.ReservationResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/reservation")
public class ReservationController {
    private final ReservationServiceInterface service;

    public ReservationController(ReservationServiceInterface service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@RequestBody ReservationRequestDTO reservationRequestDTO, Authentication auth) {
        String email = auth.getName();
        ReservationResponseDTO reservationResponse = service.createReservation(reservationRequestDTO, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationResponse);

     }
     @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id,Authentication auth) {
        String email = auth.getName();
        String userRole = auth.getAuthorities().stream()
                .findFirst().map(GrantedAuthority::getAuthority).orElse("");
        ReservationResponseDTO reservation = service.getReservationById(id,email,userRole);
        return ResponseEntity.ok(reservation);
     }
     @GetMapping("/my-reservation")
    public ResponseEntity<List<ReservationResponseDTO>> getReservationsByUser(Authentication auth) {
        String email = auth.getName();
        List<ReservationResponseDTO> reservation = service.getReservationsByUser(email);
        return ResponseEntity.ok(reservation);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations(){
        return ResponseEntity.ok(service.getAllReservations());
    }
    @PatchMapping ("/{id}/cancel")
    public ResponseEntity<String>  cancelReservationById(@PathVariable Long id,Authentication auth){
         String email = auth.getName();
         service.cancelReservationById(id,email);
         return ResponseEntity.ok("Reservation cancelled successfully.");
    }
}
