package com.wallet.crypto.trustapp.interact;

import com.aitivity.enterprise.wallet.aave.ATokenRepositoryType;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.PasswordStore;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AaveInfoInteract {
    private final ATokenRepositoryType atokenRepository;
    private final PasswordStore passwordStore;

    @Inject
    public AaveInfoInteract(ATokenRepositoryType atokenRepository, PasswordStore passwordStore) {
        this.atokenRepository = atokenRepository;
        this.passwordStore = passwordStore;
    }

    public Single<String> getExchangeRate(Wallet from) {
        return passwordStore.getPassword(from)
                .flatMap(password ->
                        atokenRepository.getExchangeRate(from)
                                .observeOn(AndroidSchedulers.mainThread()));
    }

    public Single<String> getBalance(Wallet from) {
        return passwordStore.getPassword(from)
                .flatMap(password ->
                        atokenRepository.getBalance(from)
                                .observeOn(AndroidSchedulers.mainThread()));
    }

}

