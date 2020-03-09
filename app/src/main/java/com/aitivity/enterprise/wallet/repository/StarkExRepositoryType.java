package com.aitivity.enterprise.wallet.repository;

import com.wallet.crypto.trustapp.entity.Wallet;

import io.reactivex.Single;

public interface StarkExRepositoryType {
    Single<String> register(Wallet from, String starkKey, String starkSignature, String password);
}
