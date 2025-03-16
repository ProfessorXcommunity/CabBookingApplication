package com.BookYourCab.CarBookingApp.Strategy;

import com.BookYourCab.CarBookingApp.Entity.Driver;
import com.BookYourCab.CarBookingApp.Entity.RideRequest;

import java.util.List;

public interface DriverMatching {

    List<Driver> findMatchingDriver(RideRequest rideRequest);
}
