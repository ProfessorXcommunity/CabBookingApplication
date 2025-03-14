package com.BookYourCab.CarBookingApp.Strategy.StrategyImpl;

import com.BookYourCab.CarBookingApp.Entity.Driver;
import com.BookYourCab.CarBookingApp.Entity.RideRequest;
import com.BookYourCab.CarBookingApp.Repository.DriverRepository;
import com.BookYourCab.CarBookingApp.Strategy.DriverMatching;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FindHighestRatedDriver implements DriverMatching {

    private final DriverRepository driverRepository;

    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {

        return driverRepository.findTenNearbyTopRatedDriver(rideRequest.getPickUpLocation());
    }
}
