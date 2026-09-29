package com.rovinn.moviereservation.Service;

import com.rovinn.moviereservation.model.UserRequestDTO;
import com.rovinn.moviereservation.model.UserResponseDTO;

import java.util.List;

public interface UserServiceInterface {
    UserResponseDTO register(UserRequestDTO register);
    UserResponseDTO login(UserRequestDTO login);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO findUserByEmail(String email);
}
