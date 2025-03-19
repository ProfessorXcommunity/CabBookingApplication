package com.BookYourCab.CarBookingApp.Services.Implementations;

import com.BookYourCab.CarBookingApp.Dto.DriverDto;
import com.BookYourCab.CarBookingApp.Dto.RideDto;
import com.BookYourCab.CarBookingApp.Dto.RideRequestDto;
import com.BookYourCab.CarBookingApp.Dto.RiderDto;
import com.BookYourCab.CarBookingApp.Entity.Ride;
import com.BookYourCab.CarBookingApp.Entity.RideRequest;
import com.BookYourCab.CarBookingApp.Entity.Rider;
import com.BookYourCab.CarBookingApp.Entity.User;
import com.BookYourCab.CarBookingApp.Entity.enums.RideRequestStatus;
import com.BookYourCab.CarBookingApp.Entity.enums.RideStatus;
import com.BookYourCab.CarBookingApp.Exceptions.ResourceNotFoundException;
import com.BookYourCab.CarBookingApp.Repository.RideRequestRepository;
import com.BookYourCab.CarBookingApp.Repository.RiderRepository;
import com.BookYourCab.CarBookingApp.Services.DriverService;
import com.BookYourCab.CarBookingApp.Services.RideService;
import com.BookYourCab.CarBookingApp.Services.RiderService;
import com.BookYourCab.CarBookingApp.Strategy.DriverMatching;
import com.BookYourCab.CarBookingApp.Strategy.RideFareCalculation;
import com.BookYourCab.CarBookingApp.Strategy.StrategyManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
@Slf4j
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper;
    private final RideFareCalculation rideFareCalculation;
    private final DriverMatching driverMatching;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final StrategyManager strategyManager;
    private final RideService rideService;
    private final DriverService driverService;

    @Transactional
    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider = getCurrentRider();
//        todo converting dto to entity class
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);
//        todo changing the ride request status
//        rideRequest.getPickUpLocation().setSRID(4326);
//        rideRequest.getDropOffLocation().setSRID(4326);

        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);
//        todo fare calculation from dto because dto -> data transfer object
        Double fare = strategyManager.rideFareCalculation().calculateFare(rideRequest);
        rideRequest.setFare(fare);
        log.info("calculated fare" +" "+ fare);
//        todo saved the ride request on repo, repo is like database
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
//        todo driver matching strategy-->driver matching is private so we are using entity instead of DTO
        strategyManager.driverMatching(rider.getRating()).findMatchingDriver(rideRequest);
//        todo send notifications to all the available drivers

//        todo now return the dto--> services work means dto->entity->do the work->entity->dto
        return modelMapper.map(savedRideRequest,RideRequestDto.class);
//        log.info(rideRequest.toString());

    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider does not own this ride : "+ ride.getRider());
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride status is not Confirmed yet, status :"+ride.getRideStatus());
        }

        Ride mappedRide = rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(),true);

        return modelMapper.map(mappedRide,RideDto.class);
    }

    @Override
    public DriverDto rateRider(Long riderId, Integer rating) {
        return null;
    }

    @Override
    public RiderDto getMyProfile() {
        return null;
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider.getId(), pageRequest)
                .map(
                        ride -> modelMapper.map(ride, RideDto.class)
                );

    }

    @Override
    public Rider createNewUser(User user) {
        Rider rider = Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
//        todo Spring security setup here
        return riderRepository.findById(1L).orElseThrow(()->new ResourceNotFoundException(
                "Rider not found"+1
        ));
    }
}
