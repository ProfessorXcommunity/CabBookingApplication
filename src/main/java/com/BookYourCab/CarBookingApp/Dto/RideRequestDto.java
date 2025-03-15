package com.BookYourCab.CarBookingApp.Dto;

import com.BookYourCab.CarBookingApp.Entity.Rider;
import com.BookYourCab.CarBookingApp.Entity.enums.PaymentMethod;
import com.BookYourCab.CarBookingApp.Entity.enums.RideRequestStatus;
import com.BookYourCab.CarBookingApp.Utils.GeometryUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

    private Long id;

    private PointDto pickUpLocation;

    private PointDto dropOffLocation;

    private LocalDateTime requestedTime;

    private Rider rider;

    private PaymentMethod paymentMethod;

    private RideRequestStatus rideRequestStatus;

//    public Point getPickUpPoint() {
//        return GeometryUtil.createPoint(pickUpLocation);
//    }
//
//    public Point getDropOffPoint() {
//        return GeometryUtil.createPoint(dropOffLocation);
//    }
}
