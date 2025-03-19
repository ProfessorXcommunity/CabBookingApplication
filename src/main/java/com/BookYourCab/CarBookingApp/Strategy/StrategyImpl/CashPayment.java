package com.BookYourCab.CarBookingApp.Strategy.StrategyImpl;

import com.BookYourCab.CarBookingApp.Entity.Driver;
import com.BookYourCab.CarBookingApp.Entity.Payment;
import com.BookYourCab.CarBookingApp.Entity.Wallet;
import com.BookYourCab.CarBookingApp.Entity.enums.PaymentStatus;
import com.BookYourCab.CarBookingApp.Entity.enums.TransactionMethod;
import com.BookYourCab.CarBookingApp.Repository.PaymentRepository;
import com.BookYourCab.CarBookingApp.Services.WalletService;
import com.BookYourCab.CarBookingApp.Strategy.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashPayment implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;

    @Override
    public void processPayment(Payment payment) {
        Driver driver = payment.getRide().getDriver();

        Wallet driverWallet = walletService.findWalletByUser(driver.getUser());
        double platformFees = payment.getAmount() * PLATFORM_COMMISSION;

        walletService.deductFromWallet(driver.getUser(), platformFees,null,
                payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
