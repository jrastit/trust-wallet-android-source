package com.aitivity.enterprise.wallet.interact;

import com.aitivity.enterprise.wallet.repository.ATokenRepositoryType;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.PasswordStore;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class GetAAVEBalance {

    private final ATokenRepositoryType aTokenRepository;
    private final PasswordStore passwordStore;

    @Inject
    public GetAAVEBalance(
            ATokenRepositoryType aTokenRepository,
            PasswordStore passwordStore) {
        this.aTokenRepository = aTokenRepository;
        this.passwordStore = passwordStore;
    }

    public Single<Map<String, String>> getBalance(Wallet from) {
        return passwordStore.getPassword(from)
                .flatMap(password -> {
                    Map<String, String> balances = new HashMap<>();
                    balances.put("ETH", aTokenRepository.getBalance(from).blockingGet());
                    return Single.just(balances);
                }).observeOn(AndroidSchedulers.mainThread());
    }

}