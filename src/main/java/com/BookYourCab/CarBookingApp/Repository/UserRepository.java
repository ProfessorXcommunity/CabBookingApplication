package com.BookYourCab.CarBookingApp.Repository;

import com.BookYourCab.CarBookingApp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
