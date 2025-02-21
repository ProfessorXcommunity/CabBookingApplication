package com.BookYourCab.CarBookingApp.Strategy;

import com.BookYourCab.CarBookingApp.Dto.RideRequestDto;
import com.BookYourCab.CarBookingApp.Entity.Driver;

import java.util.List;

public interface DriverMatching {

    List<Driver> findMatchingDriver(RideRequestDto rideRequestDto);
}
