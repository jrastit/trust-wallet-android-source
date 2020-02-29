package com.aitivity.enterprise.wallet.repository;

import com.wallet.crypto.trustapp.entity.Wallet;

import io.reactivex.Single;

public interface ATokenRepositoryType {
    Single<String> getBalance(Wallet from);
    Single<String> redeem(Wallet from, double balance, String password);
    Single<String> test(Wallet from, String password);
}
