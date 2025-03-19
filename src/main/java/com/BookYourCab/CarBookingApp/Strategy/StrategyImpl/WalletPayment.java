package com.BookYourCab.CarBookingApp.Strategy.StrategyImpl;

import com.BookYourCab.CarBookingApp.Entity.Driver;
import com.BookYourCab.CarBookingApp.Entity.Payment;
import com.BookYourCab.CarBookingApp.Entity.Rider;
import com.BookYourCab.CarBookingApp.Entity.enums.PaymentStatus;
import com.BookYourCab.CarBookingApp.Entity.enums.TransactionMethod;
import com.BookYourCab.CarBookingApp.Repository.PaymentRepository;
import com.BookYourCab.CarBookingApp.Services.PaymentService;
import com.BookYourCab.CarBookingApp.Services.WalletService;
import com.BookYourCab.CarBookingApp.Strategy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//Rider 250 , Driver 500
//Ride cost is 100, commission = 30
//Rider 150 , Driver 570

@Service
@RequiredArgsConstructor
public class WalletPayment implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();

        walletService.deductFromWallet(rider.getUser(), payment.getAmount(),
                null, payment.getRide(), TransactionMethod.RIDE);
        double driverIncome = payment.getAmount() * (1 - PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(), driverIncome,
                null, payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
