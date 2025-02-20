package com.BookYourCab.CarBookingApp.Services;

import com.BookYourCab.CarBookingApp.Dto.DriverDto;
import com.BookYourCab.CarBookingApp.Dto.RideDto;
import com.BookYourCab.CarBookingApp.Dto.RiderDto;

import java.util.List;

public interface DriverService {
    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long riderId , Integer rating);

    DriverDto getMyProfile();

    List<RideDto> getAllMyRides();
}
