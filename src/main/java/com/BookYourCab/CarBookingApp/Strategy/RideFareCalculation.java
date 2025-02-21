package com.BookYourCab.CarBookingApp.Strategy;

import com.BookYourCab.CarBookingApp.Dto.RideRequestDto;

public interface RideFareCalculation {
    double calculateFare(RideRequestDto rideRequestDto);
}
