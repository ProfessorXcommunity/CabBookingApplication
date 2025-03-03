package com.BookYourCab.CarBookingApp.Strategy.StrategyImpl;

import com.BookYourCab.CarBookingApp.Dto.RideRequestDto;
import com.BookYourCab.CarBookingApp.Entity.RideRequest;
import com.BookYourCab.CarBookingApp.Services.DistanceService;
import com.BookYourCab.CarBookingApp.Strategy.RideFareCalculation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Primary
public class DefaultFareCalculationOfRider implements RideFareCalculation {
    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 3;

    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance = distanceService.calculateDistance(rideRequest.getPickUpLocation(),
                rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLE*SURGE_FACTOR;
    }
}
