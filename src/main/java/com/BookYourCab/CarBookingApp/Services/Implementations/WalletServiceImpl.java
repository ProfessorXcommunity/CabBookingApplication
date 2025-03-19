package com.BookYourCab.CarBookingApp.Services.Implementations;

import com.BookYourCab.CarBookingApp.Entity.Ride;
import com.BookYourCab.CarBookingApp.Entity.User;
import com.BookYourCab.CarBookingApp.Entity.Wallet;
import com.BookYourCab.CarBookingApp.Entity.WalletTransaction;
import com.BookYourCab.CarBookingApp.Entity.enums.TransactionMethod;
import com.BookYourCab.CarBookingApp.Entity.enums.TransactionType;
import com.BookYourCab.CarBookingApp.Exceptions.ResourceNotFoundException;
import com.BookYourCab.CarBookingApp.Repository.WalletRepository;
import com.BookYourCab.CarBookingApp.Services.WalletService;
import com.BookYourCab.CarBookingApp.Services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletTransactionService walletTransactionService;
    private final WalletRepository walletRepository;
    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount,String transactionId,
                                   Ride ride,TransactionMethod transactionMethod) {
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(()->new ResourceNotFoundException("User not found" + user.getId()));
        wallet.setBalance(wallet.getBalance()+amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.CREDIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductFromWallet(User user, Double amount,String transactionId,
                                   Ride ride,TransactionMethod transactionMethod) {
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(()->new ResourceNotFoundException("User not found" + user.getId()));
        wallet.setBalance(wallet.getBalance() - amount);
        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .wallet(wallet)
                .transactionType(TransactionType.DEBIT)
                .transactionMethod(transactionMethod)
                .amount(amount)
                .build();

//        walletTransactionService.createNewWalletTransaction(walletTransaction);
        wallet.getWalletTransactionList().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(()->new ResourceNotFoundException("Wallet id is not found" + walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findWalletByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(()->new ResourceNotFoundException("User not found !"));
    }
}
