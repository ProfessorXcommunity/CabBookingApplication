package com.BookYourCab.CarBookingApp.Services;

import com.BookYourCab.CarBookingApp.Dto.DriverDto;
import com.BookYourCab.CarBookingApp.Dto.SignupDto;
import com.BookYourCab.CarBookingApp.Dto.UserDto;

public interface AuthService {

    String[] login(String email , String password);

    UserDto signup(SignupDto signupDto);

    DriverDto onBoardNewDriver(Long userId,String vehicleId);

    String refreshToken(String refreshToken);
}
