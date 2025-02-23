package com.BookYourCab.CarBookingApp.Services.Implementations;

import com.BookYourCab.CarBookingApp.Dto.DriverDto;
import com.BookYourCab.CarBookingApp.Dto.SignupDto;
import com.BookYourCab.CarBookingApp.Dto.UserDto;
import com.BookYourCab.CarBookingApp.Entity.Rider;
import com.BookYourCab.CarBookingApp.Entity.User;
import com.BookYourCab.CarBookingApp.Entity.enums.Roles;
import com.BookYourCab.CarBookingApp.Repository.UserRepository;
import com.BookYourCab.CarBookingApp.Services.AuthService;
import com.BookYourCab.CarBookingApp.Services.RiderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final RiderService riderService;

    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public UserDto Signup(SignupDto signupDto) {
        userRepository.findByEmail(signupDto.getEmail()).orElseThrow(()->
                new RuntimeException("user already exists" + signupDto.getEmail()));


        User mappedUser = modelMapper.map(signupDto,User.class);
        mappedUser.setRoles(Set.of(Roles.RIDER));
        User savedUser = userRepository.save(mappedUser);

//        todo create new user related to entity
        riderService.createNewUser(savedUser);
//        todo wallet related services
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public DriverDto onBoardNewDriver(Long userId) {
        return null;
    }
}
