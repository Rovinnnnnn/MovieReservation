package com.rovinn.moviereservation.Repository;

import com.rovinn.moviereservation.model.MovieData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieData, Long> {
    boolean existsByTitle(String title);
    Optional<MovieData> findByTitle(String title);
}
