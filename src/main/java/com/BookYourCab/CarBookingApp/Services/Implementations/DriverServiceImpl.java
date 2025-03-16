package com.BookYourCab.CarBookingApp.Services.Implementations;

import com.BookYourCab.CarBookingApp.Dto.DriverDto;
import com.BookYourCab.CarBookingApp.Dto.RideDto;
import com.BookYourCab.CarBookingApp.Dto.RiderDto;
import com.BookYourCab.CarBookingApp.Entity.Driver;
import com.BookYourCab.CarBookingApp.Entity.Ride;
import com.BookYourCab.CarBookingApp.Entity.RideRequest;
import com.BookYourCab.CarBookingApp.Entity.enums.RideRequestStatus;
import com.BookYourCab.CarBookingApp.Exceptions.ResourceNotFoundException;
import com.BookYourCab.CarBookingApp.Repository.DriverRepository;
import com.BookYourCab.CarBookingApp.Services.DriverService;
import com.BookYourCab.CarBookingApp.Services.RideRequestService;
import com.BookYourCab.CarBookingApp.Services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final RideRequestService rideRequestService;
    private final DriverRepository driverRepository;
    private final RideService rideService;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public RideDto acceptRide(Long rideRequestId) {
        RideRequest rideRequest = rideRequestService.findRideRequestById(rideRequestId);
        if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
            throw new RuntimeException("Ride request can't be accepted, status is :"+ rideRequest.getRideRequestStatus());
        }

        Driver currentDriver = getCurrentDriver();
        if(!currentDriver.getAvailable()){
            throw new RuntimeException("Driver can't accept the ride due to unavailability");
        }

        Ride ride = rideService.createNewRide(rideRequest,currentDriver);
        return  modelMapper.map(ride,RideDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto startRide(Long rideId) {
        return null;
    }

    @Override
    public RideDto endRide(Long rideId) {
        return null;
    }

    @Override
    public RiderDto rateRider(Long riderId, Integer rating) {
        return null;
    }

    @Override
    public DriverDto getMyProfile() {
        return null;
    }

    @Override
    public List<RideDto> getAllMyRides() {
        return List.of();
    }

    @Override
    public Driver getCurrentDriver() {
        return driverRepository.findById(2L)
                .orElseThrow(()-> new ResourceNotFoundException("No drivers found with id "+2));

    }
}
