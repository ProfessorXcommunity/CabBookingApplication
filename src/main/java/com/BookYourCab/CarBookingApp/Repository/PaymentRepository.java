package com.BookYourCab.CarBookingApp.Repository;

import com.BookYourCab.CarBookingApp.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
