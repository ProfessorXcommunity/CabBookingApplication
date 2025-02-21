package com.BookYourCab.CarBookingApp.Strategy.StrategyImpl;

import com.BookYourCab.CarBookingApp.Dto.RideRequestDto;
import com.BookYourCab.CarBookingApp.Strategy.RideFareCalculation;
import org.springframework.stereotype.Service;

@Service
public class SurgedFareCalculationOfRider implements RideFareCalculation {
    @Override
    public double calculateFare(RideRequestDto rideRequestDto) {
        return 0;
    }
}
