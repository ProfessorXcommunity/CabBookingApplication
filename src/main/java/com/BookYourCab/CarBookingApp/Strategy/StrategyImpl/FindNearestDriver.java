package com.BookYourCab.CarBookingApp.Strategy.StrategyImpl;

import com.BookYourCab.CarBookingApp.Dto.RideRequestDto;
import com.BookYourCab.CarBookingApp.Entity.Driver;
import com.BookYourCab.CarBookingApp.Strategy.DriverMatching;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FindNearestDriver implements DriverMatching {
    @Override
    public List<Driver> findMatchingDriver(RideRequestDto rideRequestDto) {
        return List.of();
    }
}
