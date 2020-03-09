package com.aitivity.enterprise.wallet.interact;

import android.util.Log;

import com.aitivity.enterprise.wallet.repository.StarkExRepositoryType;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.PasswordStore;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StarkExInteract {

    private final String TAG = "StarkExInteract";

    private final StarkExRepositoryType starkExRepository;
    private final PasswordStore passwordStore;

    @Inject
    public StarkExInteract(
                               StarkExRepositoryType starkExRepositoryType,
                               PasswordStore passwordStore) {
        this.starkExRepository = starkExRepositoryType;
        this.passwordStore = passwordStore;
    }

    public Single<String> register(Wallet from, String StarkKey, String StarkSignature){
        Log.d(TAG, "register: " + starkExRepository);

        return passwordStore.getPassword(from)
                .subscribeOn(Schedulers.io())
                .flatMap(password ->
                        starkExRepository
                                .register(from, StarkKey, StarkSignature, password)
                                .observeOn(AndroidSchedulers.mainThread()));
    }


}

