package com.BookYourCab.CarBookingApp.Strategy.StrategyImpl;

import com.BookYourCab.CarBookingApp.Entity.Driver;
import com.BookYourCab.CarBookingApp.Entity.Payment;
import com.BookYourCab.CarBookingApp.Services.WalletService;
import com.BookYourCab.CarBookingApp.Strategy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPayment implements PaymentStrategy {
    private final WalletService walletService;
    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();


    }
}
