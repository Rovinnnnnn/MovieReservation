package com.rovinn.moviereservation.Repository;

import com.rovinn.moviereservation.model.ReservationData;

import com.rovinn.moviereservation.model.Status;
import com.rovinn.moviereservation.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository<ReservationData,Long> {
    List<ReservationData> findByUser(UserData email);
    boolean existsByShowTimeIdAndSeatNumberAndStatusNot(Long showTimeId, String seatNumber,Status status);
    List<ReservationData> findByShowTimeIdAndStatusNot(Long showTimeId, Status status);
}
