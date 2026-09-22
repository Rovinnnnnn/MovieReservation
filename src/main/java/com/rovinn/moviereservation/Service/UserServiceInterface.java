package com.rovinn.moviereservation.Service;

import com.rovinn.moviereservation.model.UserRequestDTO;
import com.rovinn.moviereservation.model.UserResponseDTO;

public interface UserServiceInterface {
    UserResponseDTO register(UserRequestDTO register);
    UserResponseDTO login(UserRequestDTO login);
}
