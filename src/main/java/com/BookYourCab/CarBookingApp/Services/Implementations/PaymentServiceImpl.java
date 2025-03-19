package com.BookYourCab.CarBookingApp.Services.Implementations;

import com.BookYourCab.CarBookingApp.Entity.Payment;
import com.BookYourCab.CarBookingApp.Entity.Ride;
import com.BookYourCab.CarBookingApp.Entity.enums.PaymentStatus;
import com.BookYourCab.CarBookingApp.Repository.PaymentRepository;
import com.BookYourCab.CarBookingApp.Services.PaymentService;
import com.BookYourCab.CarBookingApp.Strategy.PaymentStrategy;
import com.BookYourCab.CarBookingApp.Strategy.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentStrategyManager paymentStrategyManager;
    private final PaymentRepository paymentRepository;
    @Override
    public void paymentProcess(Payment payment) {
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);
    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment = Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}
