package com.BookYourCab.CarBookingApp.Strategy;

import com.BookYourCab.CarBookingApp.Entity.Payment;

public interface PaymentStrategy {
    Double PLATFORM_COMMISSION = 0.3;

    void processPayment(Payment payment);
}
