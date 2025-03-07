package com.BookYourCab.CarBookingApp.Controller;

import com.BookYourCab.CarBookingApp.Dto.SignupDto;
import com.BookYourCab.CarBookingApp.Dto.UserDto;
import com.BookYourCab.CarBookingApp.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    UserDto signUp(@RequestBody SignupDto signupDto){
        return authService.Signup(signupDto);
    }

}
