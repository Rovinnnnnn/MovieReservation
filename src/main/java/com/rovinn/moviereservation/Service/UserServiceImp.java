package com.rovinn.moviereservation.Service;

import com.rovinn.moviereservation.JwtUtil;
import com.rovinn.moviereservation.Repository.UserRepository;
import com.rovinn.moviereservation.model.UserData;
import com.rovinn.moviereservation.model.UserRequestDTO;
import com.rovinn.moviereservation.model.UserResponseDTO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
//this class contain Business logic. And Spring should manage it's as bean
public class UserServiceImp implements UserServiceInterface {
  private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public UserServiceImp(UserRepository userRepository, JwtUtil jwtUtil) {
      this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

  @Override
  public UserResponseDTO register(UserRequestDTO register) {
      UserData userData = new UserData();
      userData.setEmail(register.getEmail());

      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      userData.setPassword(passwordEncoder.encode(register.getPassword()));
      userRepository.save(userData);

      UserResponseDTO userResponseDTO = new UserResponseDTO();
      userResponseDTO.setId(userData.getId());
      userResponseDTO.setEmail(register.getEmail());
      userResponseDTO.setToken("Token will be shown after Login");
      return userResponseDTO;
  }
    @Override
    public UserResponseDTO login(UserRequestDTO login) {
        UserData find =  userRepository.findByEmail(login.getEmail()).orElseThrow(()->new RuntimeException("User not found"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(login.getPassword(), find.getPassword());
        if(!matches) {
            throw new RuntimeException("Invalid email or password");
        }
        String token = jwtUtil.generateToken(login.getEmail());
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(find.getId());
        userResponseDTO.setEmail(find.getEmail());
        userResponseDTO.setToken(token);
        return userResponseDTO;
    }
}
