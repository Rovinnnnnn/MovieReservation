    package com.rovinn.moviereservation.Controller;

    import com.rovinn.moviereservation.Service.ShowTimeServiceInterface;
    import com.rovinn.moviereservation.model.ShowTimeRequestDTO;
    import com.rovinn.moviereservation.model.ShowTimeResponseDTO;
    import jakarta.validation.Valid;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/showtimes")
    public class ShowTimeController {
        private final ShowTimeServiceInterface service;
        public ShowTimeController(ShowTimeServiceInterface service){
            this.service = service;
        }
        @PostMapping
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<ShowTimeResponseDTO> createShowTime(@Valid @RequestBody ShowTimeRequestDTO requestDTO){
            return ResponseEntity.ok(service.createShowTime(requestDTO));
        }
        @GetMapping("/{id}/seats")
        public ResponseEntity<List<String>> getTakenSeats(@PathVariable Long id) {
            return ResponseEntity.ok(service.getTakenSeats(id));
        }
        @GetMapping("/{movieId}")
        public ResponseEntity<List<ShowTimeResponseDTO>> getShowTimesByMovieId (@PathVariable Long movieId){
            return ResponseEntity.ok(service.getShowTimesByMovieId(movieId));
        }
        @DeleteMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<Void> deleteShowTime(@PathVariable Long id){
            service.deleteShowTime(id);
            return ResponseEntity.noContent().build();
        }
    }
