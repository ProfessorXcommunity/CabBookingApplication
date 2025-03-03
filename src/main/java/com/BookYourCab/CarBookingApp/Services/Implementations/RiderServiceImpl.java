package com.BookYourCab.CarBookingApp.Services.Implementations;

import com.BookYourCab.CarBookingApp.Dto.DriverDto;
import com.BookYourCab.CarBookingApp.Dto.RideDto;
import com.BookYourCab.CarBookingApp.Dto.RideRequestDto;
import com.BookYourCab.CarBookingApp.Dto.RiderDto;
import com.BookYourCab.CarBookingApp.Entity.RideRequest;
import com.BookYourCab.CarBookingApp.Entity.Rider;
import com.BookYourCab.CarBookingApp.Entity.User;
import com.BookYourCab.CarBookingApp.Entity.enums.RideRequestStatus;
import com.BookYourCab.CarBookingApp.Repository.RideRequestRepository;
import com.BookYourCab.CarBookingApp.Repository.RiderRepository;
import com.BookYourCab.CarBookingApp.Services.RiderService;
import com.BookYourCab.CarBookingApp.Strategy.DriverMatching;
import com.BookYourCab.CarBookingApp.Strategy.RideFareCalculation;
import com.BookYourCab.CarBookingApp.Strategy.StrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
//        todo converting dto to entity class
        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);
//        todo changing the ride request status
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
//        todo fare calculation from dto because dto -> data transfer object
        Double fare = strategyManager.rideFareCalculation().calculateFare(rideRequest);
        rideRequest.setFare(fare);
//        todo saved the ride request on repo, repo is like database
        RideRequest savedRideRequest = rideRequestRepository.save(rideRequest);
//        todo driver matching strategy-->driver matching is private so we are using entity instead of DTO
        strategyManager.driverMatching(4.7).findMatchingDriver(rideRequest);

//        todo now return the dto--> services work means dto->entity->do the work->entity->dto
        return modelMapper.map(savedRideRequest,RideRequestDto.class);
//        log.info(rideRequest.toString());

    }

    @Override
    public RideDto cancelRIde(Long rideId) {
        return null;
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
    public List<RideDto> getAllMyRides() {
        return List.of();
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
}
