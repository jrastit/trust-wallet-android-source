package com.aitivity.enterprise.wallet.interact;

import com.aitivity.enterprise.wallet.repository.ATokenRepositoryType;
import com.aitivity.enterprise.wallet.repository.LendingPoolRepositoryType;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.PasswordStore;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class AaveInfoInteract {
    private final LendingPoolRepositoryType lendingPoolRepository;
    private final ATokenRepositoryType atokenRepository;
    private final PasswordStore passwordStore;

    @Inject
    public AaveInfoInteract(LendingPoolRepositoryType lendingPoolRepository, ATokenRepositoryType atokenRepository, PasswordStore passwordStore) {
        this.lendingPoolRepository = lendingPoolRepository;
        this.atokenRepository = atokenRepository;
        this.passwordStore = passwordStore;
    }

    public Single<String> getBalance(Wallet from) {
        return passwordStore.getPassword(from)
                .flatMap(password ->
                        atokenRepository.getBalance(from)
                                .observeOn(AndroidSchedulers.mainThread()));
    }

    public Single<String> redeem(Wallet from, double amount){
        return passwordStore.getPassword(from)
                .flatMap(password ->
                        atokenRepository
                                .redeem(from, amount, password));
    }

    public Single<String> test(Wallet from) {
        return passwordStore.getPassword(from)
                .flatMap(password ->
                        atokenRepository.test(from, password)
                                .observeOn(AndroidSchedulers.mainThread()));
    }

    public Single<String> deposit(Wallet from, double amount){
        return passwordStore.getPassword(from)
                .flatMap(password ->
                        lendingPoolRepository
                                .deposit(from, amount, password));
    }

}

