package com.rovinn.moviereservation.Repository;

import com.rovinn.moviereservation.model.ShowTimeData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowTimeRepository extends JpaRepository<ShowTimeData,Long> {
}
