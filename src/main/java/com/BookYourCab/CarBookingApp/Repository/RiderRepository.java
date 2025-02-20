package com.BookYourCab.CarBookingApp.Repository;

import com.BookYourCab.CarBookingApp.Entity.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface RiderRepository extends JpaRepository<Rider,Long> {
}
