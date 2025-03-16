package com.BookYourCab.CarBookingApp.Controller;

import com.BookYourCab.CarBookingApp.Dto.RideDto;
import com.BookYourCab.CarBookingApp.Dto.RideStartDto;
import com.BookYourCab.CarBookingApp.Services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/driver")
public class DriverController {
    private final DriverService driverService;

    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDto> acceptRide(@PathVariable Long rideRequestId){

        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }
    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDto> startRide(@PathVariable Long rideRequestId,
                                             @RequestBody RideStartDto rideStartDto){

        return ResponseEntity.ok(driverService.startRide(rideRequestId,rideStartDto.getOtp()));
    }
}
