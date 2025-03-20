package com.BookYourCab.CarBookingApp.Services;

import com.BookYourCab.CarBookingApp.Entity.User;
import com.BookYourCab.CarBookingApp.Exceptions.ResourceNotFoundException;
import com.BookYourCab.CarBookingApp.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElse(null);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user not found"));
    }
}
