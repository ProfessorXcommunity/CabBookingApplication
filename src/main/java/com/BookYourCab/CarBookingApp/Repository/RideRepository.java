package com.BookYourCab.CarBookingApp.Repository;

import com.BookYourCab.CarBookingApp.Entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride,Long> {
}
