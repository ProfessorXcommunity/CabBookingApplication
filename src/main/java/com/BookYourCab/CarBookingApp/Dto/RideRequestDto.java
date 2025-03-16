package com.BookYourCab.CarBookingApp.Dto;

import com.BookYourCab.CarBookingApp.Entity.enums.PaymentMethod;
import com.BookYourCab.CarBookingApp.Entity.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

    private Long id;

    private PointDto pickUpLocation;

    private PointDto dropOffLocation;

    private LocalDateTime requestedTime;

    private RiderDto rider;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;

    private Double fare;


}
