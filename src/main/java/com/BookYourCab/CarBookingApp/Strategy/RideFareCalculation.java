package com.BookYourCab.CarBookingApp.Strategy;

import com.BookYourCab.CarBookingApp.Entity.RideRequest;

public interface RideFareCalculation {

    final double RIDE_FARE_MULTIPLE = 10;

    double calculateFare(RideRequest rideRequest);
}
