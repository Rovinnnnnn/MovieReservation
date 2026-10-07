package com.rovinn.moviereservation.Repository;

import com.rovinn.moviereservation.model.ShowTimeData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTimeData,Long> {
    List<ShowTimeData> findByMovieId(Long movieId);
}
