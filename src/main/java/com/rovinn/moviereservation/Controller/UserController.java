package com.rovinn.moviereservation.Controller;

import com.rovinn.moviereservation.Service.UserServiceInterface;
import com.rovinn.moviereservation.model.UserRequestDTO;
import com.rovinn.moviereservation.model.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceInterface service;
    public UserController(UserServiceInterface service) {
        this.service = service;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO register) {
        return ResponseEntity.ok(service.register(register));
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO login) {
        return ResponseEntity.ok(service.login(login));
    }
}
