package com.BookYourCab.CarBookingApp.Repository;

import com.BookYourCab.CarBookingApp.Entity.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Query("SELECT d.* , ST_Distance(d.current_location,:pickUpLocation) AS distance"+
            "FROM drivers as d"+
            "where available = true AND ST_Within(d.current_location,:pickUpLocation,10000)"+
            "ORDER BY DISTANCE"+
            "LIMIT 10"
    )
    List<Driver> findNearestTenDriver(Point pickUpLocation);
}
