package com.rovinn.moviereservation.Repository;

import com.rovinn.moviereservation.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Long> {
    Optional<UserData> findByEmail(String email);

}
