package com.BookYourCab.CarBookingApp.Controller;

import com.BookYourCab.CarBookingApp.Dto.RideRequestDto;
import com.BookYourCab.CarBookingApp.Services.RiderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
@Slf4j
public class RiderController {

    private final RiderService riderService;
    @PostMapping("/requestRide")
    private ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
        log.info("Received RideRequestDto: {}", rideRequestDto);
        return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
    }
}
