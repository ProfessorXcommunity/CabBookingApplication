package com.BookYourCab.CarBookingApp.Services;

import com.BookYourCab.CarBookingApp.Entity.Payment;
import com.BookYourCab.CarBookingApp.Entity.Ride;
import com.BookYourCab.CarBookingApp.Entity.enums.PaymentStatus;

public interface PaymentService {
    void paymentProcess(Payment payment);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
