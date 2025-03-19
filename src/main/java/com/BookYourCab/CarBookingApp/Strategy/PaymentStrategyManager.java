package com.BookYourCab.CarBookingApp.Strategy;

import com.BookYourCab.CarBookingApp.Entity.enums.PaymentMethod;
import com.BookYourCab.CarBookingApp.Strategy.StrategyImpl.CashPayment;
import com.BookYourCab.CarBookingApp.Strategy.StrategyImpl.WalletPayment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final WalletPayment walletPayment;
    private final CashPayment cashPayment;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case WALLET -> walletPayment;
            case CASH -> cashPayment;
        };
    }

}
