package com.BookYourCab.CarBookingApp.Services.Implementations;

import com.BookYourCab.CarBookingApp.Services.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        return 0;
    }
}
