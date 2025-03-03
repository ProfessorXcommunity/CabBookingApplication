package com.BookYourCab.CarBookingApp.Strategy;

import com.BookYourCab.CarBookingApp.Strategy.StrategyImpl.DefaultFareCalculationOfRider;
import com.BookYourCab.CarBookingApp.Strategy.StrategyImpl.FindHighestRatedDriver;
import com.BookYourCab.CarBookingApp.Strategy.StrategyImpl.FindNearestDriver;
import com.BookYourCab.CarBookingApp.Strategy.StrategyImpl.SurgedFareCalculationOfRider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class StrategyManager {

    private final DefaultFareCalculationOfRider defaultFareCalculation;
    private final SurgedFareCalculationOfRider surgedFareCalculation;
    private final FindHighestRatedDriver findHighestRatedDriver;
    private final FindNearestDriver findNearestDriver;

    public DriverMatching driverMatching(double riderRating){
        if (riderRating > 4.5){
            return findHighestRatedDriver;
        }else {
            return findNearestDriver;
        }
    }

    public RideFareCalculation rideFareCalculation(){
//        6PM to 9PM
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
        if(isSurgeTime){
            return surgedFareCalculation;
        }else{
            return defaultFareCalculation;
        }
    }
}
