package com.BookYourCab.CarBookingApp.Repository;

import com.BookYourCab.CarBookingApp.Entity.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest,Long> {
}
