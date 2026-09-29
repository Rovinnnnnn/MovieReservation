package com.rovinn.moviereservation.Controller;

import com.rovinn.moviereservation.Service.ShowTimeServiceInterface;
import com.rovinn.moviereservation.model.ShowTimeRequestDTO;
import com.rovinn.moviereservation.model.ShowTimeResponseDTO;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ShowTimeResponseDTO> createShowTime(@RequestBody ShowTimeRequestDTO requestDTO){
        return ResponseEntity.ok(service.createShowTime(requestDTO));
    }
    @GetMapping
    public ResponseEntity<List<ShowTimeResponseDTO>> getAllShowTime (){
        return ResponseEntity.ok(service.getAllShowTime());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowTime(@PathVariable Long id){
        service.deleteShowTime(id);
        return ResponseEntity.noContent().build();
    }
}
