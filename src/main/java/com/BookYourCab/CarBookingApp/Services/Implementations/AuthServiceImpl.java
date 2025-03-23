package com.BookYourCab.CarBookingApp.Services.Implementations;

import com.BookYourCab.CarBookingApp.Dto.DriverDto;
import com.BookYourCab.CarBookingApp.Dto.SignupDto;
import com.BookYourCab.CarBookingApp.Dto.UserDto;
import com.BookYourCab.CarBookingApp.Entity.Driver;
import com.BookYourCab.CarBookingApp.Entity.User;
import com.BookYourCab.CarBookingApp.Entity.enums.Roles;
import com.BookYourCab.CarBookingApp.Exceptions.ResourceNotFoundException;
import com.BookYourCab.CarBookingApp.Exceptions.RuntimeConflictException;
import com.BookYourCab.CarBookingApp.Repository.UserRepository;
import com.BookYourCab.CarBookingApp.Security.JWTService;
import com.BookYourCab.CarBookingApp.Services.AuthService;
import com.BookYourCab.CarBookingApp.Services.DriverService;
import com.BookYourCab.CarBookingApp.Services.RiderService;
import com.BookYourCab.CarBookingApp.Services.WalletService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.BookYourCab.CarBookingApp.Entity.enums.Roles.DRIVER;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String[] login(String email, String password) {
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email,password)
        );
        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new String[]{accessToken,refreshToken};
    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
       if(userRepository.findByEmail(signupDto.getEmail()).isPresent()){
           throw new RuntimeConflictException("user already exists" + " "+signupDto.getEmail());
       }
//                .orElseThrow(()-> new RuntimeConflictException("user already exists" + " "+signupDto.getEmail()));


        User mappedUser = modelMapper.map(signupDto,User.class);
        mappedUser.setRoles(Set.of(Roles.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);

//        todo create new user related to entity
        riderService.createNewRider(savedUser);
//        todo wallet related services
        walletService.createNewWallet(savedUser);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public DriverDto onBoardNewDriver(Long userId,String vehicleId) {
        User user  = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with this id"+userId));
        if(user.getRoles().contains(DRIVER)){
            throw new RuntimeConflictException("User is already a driver");
        }
        Driver createDriver = Driver.builder()
                .user(user)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRoles().add(DRIVER);
        userRepository.save(user);
        Driver savedDriver = driverService.createNewDriver(createDriver);
        return modelMapper.map(savedDriver,DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found"+ userId));
        return jwtService.generateAccessToken(user);
    }
}
