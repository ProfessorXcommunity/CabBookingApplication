package com.BookYourCab.CarBookingApp.Services.Implementations;

import com.BookYourCab.CarBookingApp.Dto.RideRequestDto;
import com.BookYourCab.CarBookingApp.Entity.Driver;
import com.BookYourCab.CarBookingApp.Entity.Ride;
import com.BookYourCab.CarBookingApp.Entity.RideRequest;
import com.BookYourCab.CarBookingApp.Entity.Rider;
import com.BookYourCab.CarBookingApp.Entity.enums.RideRequestStatus;
import com.BookYourCab.CarBookingApp.Entity.enums.RideStatus;
import com.BookYourCab.CarBookingApp.Exceptions.ResourceNotFoundException;
import com.BookYourCab.CarBookingApp.Repository.RideRepository;
import com.BookYourCab.CarBookingApp.Services.RideRequestService;
import com.BookYourCab.CarBookingApp.Services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(()-> new ResourceNotFoundException("Ride id is not found"));

    }

    @Override
    public void matchWithDriver(RideRequestDto rideRequestDto) {

    }

    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);

        Ride ride = modelMapper.map(rideRequest, Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOTP());
        ride.setId(null);

        rideRequestService.update(rideRequest);
        return rideRepository.save(ride);
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepository.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider, pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver, pageRequest);
    }

    private String generateRandomOTP(){
        Random random = new Random();
        int otpInt = random.nextInt(10000);
        return String.format("%04d",otpInt);
    }
}
