package com.aitivity.enterprise.wallet.repository;

import com.wallet.crypto.trustapp.entity.Wallet;

import io.reactivex.Single;

public interface LendingPoolRepositoryType {
    Single<String> deposit(Wallet from, double amount, String password);
}
