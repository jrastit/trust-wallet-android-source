package com.aitivity.enterprise.wallet.repository;

import com.wallet.crypto.trustapp.entity.Wallet;

import io.reactivex.Single;

public interface ENSTestRepositoryType {
    Single<String> register(Wallet from, String addr, String name, String password);
    Single<String> getExpiryTimes(Wallet from, String name);
}
