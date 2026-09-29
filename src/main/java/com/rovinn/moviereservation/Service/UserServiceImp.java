package com.rovinn.moviereservation.Service;

import com.rovinn.moviereservation.Exception.ResourceNotFoundException;
import com.rovinn.moviereservation.JwtUtil;
import com.rovinn.moviereservation.Repository.UserRepository;
import com.rovinn.moviereservation.model.Role;
import com.rovinn.moviereservation.model.UserData;
import com.rovinn.moviereservation.model.UserRequestDTO;
import com.rovinn.moviereservation.model.UserResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
//this class contain Business logic. And Spring should manage it's as bean
public class UserServiceImp implements UserServiceInterface {
  private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
      this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

  @Override
  public UserResponseDTO register(UserRequestDTO register) {
      UserData userData = new UserData();
      userData.setEmail(register.getEmail());
      userData.setPassword(passwordEncoder.encode(register.getPassword()));
      userData.setRole(Role.ROLE_USER);
      userRepository.save(userData);

      UserResponseDTO userResponseDTO = new UserResponseDTO();
      userResponseDTO.setId(userData.getId());
      userResponseDTO.setEmail(register.getEmail());
      userResponseDTO.setToken("Token will be shown after Login");
      return userResponseDTO;
  }
    @Override
    public UserResponseDTO login(UserRequestDTO login) {
        UserData find =  userRepository.findByEmail(login.getEmail()).orElseThrow(()->new ResourceNotFoundException("User not found"));
        boolean matches = passwordEncoder.matches(login.getPassword(), find.getPassword());
        if(!matches) {
            throw new RuntimeException("Invalid email or password");
        }
        String token = jwtUtil.generateToken(find.getEmail(),find.getRole().name());
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(find.getId());
        userResponseDTO.setEmail(find.getEmail());
        userResponseDTO.setToken(token);
        return userResponseDTO;
    }
    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserResponseDTO).toList();
    }
    @Override
    public UserResponseDTO findUserByEmail(String email) {
        UserData findUser =  userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not found with email " + email));
        return mapToUserResponseDTO(findUser);
    }
    public UserResponseDTO mapToUserResponseDTO(UserData userData) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userData.getId());
        userResponseDTO.setEmail(userData.getEmail());
        return userResponseDTO;
    }
}
