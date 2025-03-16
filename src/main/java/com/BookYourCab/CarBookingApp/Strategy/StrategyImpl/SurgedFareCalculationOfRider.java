package com.BookYourCab.CarBookingApp.Strategy.StrategyImpl;

import com.BookYourCab.CarBookingApp.Entity.RideRequest;
import com.BookYourCab.CarBookingApp.Services.DistanceService;
import com.BookYourCab.CarBookingApp.Strategy.RideFareCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurgedFareCalculationOfRider implements RideFareCalculation {

    private final DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation(),
                rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLE;
    }
}
