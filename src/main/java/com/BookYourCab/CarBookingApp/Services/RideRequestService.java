package com.BookYourCab.CarBookingApp.Services;

import com.BookYourCab.CarBookingApp.Entity.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
