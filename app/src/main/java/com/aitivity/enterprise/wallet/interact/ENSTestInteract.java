package com.aitivity.enterprise.wallet.interact;

import com.aitivity.enterprise.wallet.repository.ATokenRepositoryType;
import com.aitivity.enterprise.wallet.repository.ENSTestRepositoryType;
import com.aitivity.enterprise.wallet.repository.LendingPoolRepositoryType;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.PasswordStore;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class ENSTestInteract {
    private final ENSTestRepositoryType eNSTestRepository;
    private final PasswordStore passwordStore;

    @Inject
    public ENSTestInteract(ENSTestRepositoryType eNSTestRepository,
                           PasswordStore passwordStore) {
        this.eNSTestRepository = eNSTestRepository;
        this.passwordStore = passwordStore;
    }

    public Single<String> getBalance(Wallet from, String name) {
        return passwordStore.getPassword(from)
                .flatMap(password ->
                        eNSTestRepository.getExpiryTimes(from, name)
                                .observeOn(AndroidSchedulers.mainThread()));
    }

    public Single<String> register(Wallet from, String addr, String name) {
        return passwordStore.getPassword(from)
                .flatMap(password ->
                        eNSTestRepository.register(from, addr, name, password)
                                .observeOn(AndroidSchedulers.mainThread()));
    }

}

