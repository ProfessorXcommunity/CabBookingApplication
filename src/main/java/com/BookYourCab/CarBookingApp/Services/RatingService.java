package com.BookYourCab.CarBookingApp.Services;

import com.BookYourCab.CarBookingApp.Dto.DriverDto;
import com.BookYourCab.CarBookingApp.Dto.RiderDto;
import com.BookYourCab.CarBookingApp.Entity.Ride;

public interface RatingService {
    DriverDto rateDriver(Ride ride, Integer rating);

    RiderDto rateRider(Ride ride, Integer rating);

    void createNewRating(Ride ride);
}
