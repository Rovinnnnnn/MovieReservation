package com.rovinn.moviereservation.Repository;

import com.rovinn.moviereservation.model.ReservationData;

import com.rovinn.moviereservation.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationData,Long> {
    List<ReservationData> findByUser(UserData email);
    boolean existsByShowTimeIdAndSeatNumber(Long showTimeId, String seatNumber);
}
