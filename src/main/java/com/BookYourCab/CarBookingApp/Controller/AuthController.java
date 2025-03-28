package com.BookYourCab.CarBookingApp.Controller;

import com.BookYourCab.CarBookingApp.Dto.*;
import com.BookYourCab.CarBookingApp.Services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    ResponseEntity<UserDto> signUp(@RequestBody SignupDto signupDto){
        return new ResponseEntity<>(authService.signup(signupDto), HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/onBoardNewDriver/{userId}")
    ResponseEntity<DriverDto> onBoardNewDriver(@PathVariable Long userId,@RequestBody OnBoardDriverDto onBoardDriverDto){
        return new ResponseEntity<>(authService.onBoardNewDriver(userId,onBoardDriverDto.getVehicleId()),HttpStatus.CREATED);
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse){
        String[] tokens = authService.login(loginRequestDto.getEmail(),loginRequestDto.getPassword());
        Cookie cookie = new Cookie("token",tokens[1]);
        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
    }
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest httpServletRequest){
        String refreshToken = Arrays.stream(httpServletRequest.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new AuthorizationServiceException("Refresh Token Not found"));

        String accessToken = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(new LoginResponseDto(accessToken));
    }

}
