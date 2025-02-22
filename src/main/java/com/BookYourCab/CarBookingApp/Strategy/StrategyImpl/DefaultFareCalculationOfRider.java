package com.BookYourCab.CarBookingApp.Strategy.StrategyImpl;

import com.BookYourCab.CarBookingApp.Dto.RideRequestDto;
import com.BookYourCab.CarBookingApp.Entity.RideRequest;
import com.BookYourCab.CarBookingApp.Services.DistanceService;
import com.BookYourCab.CarBookingApp.Strategy.RideFareCalculation;
import org.springframework.stereotype.Service;

@Service
public class DefaultFareCalculationOfRider implements RideFareCalculation {
    private DistanceService distanceService;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation(),
                rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLE;
    }
}
