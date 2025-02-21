package com.BookYourCab.CarBookingApp.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private Double balance;

    @OneToMany(mappedBy = "wallet",fetch = FetchType.LAZY)
    private List<WalletTrans> walletTrans;
}
