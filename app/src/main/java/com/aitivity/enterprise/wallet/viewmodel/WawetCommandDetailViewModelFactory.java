package com.aitivity.enterprise.wallet.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.aitivity.enterprise.wallet.interact.StarkExInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;

public class WawetCommandDetailViewModelFactory implements ViewModelProvider.Factory {
    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private final FindDefaultWalletInteract findDefaultWalletInteract;
    private final StarkExInteract starkExInteract;


    public WawetCommandDetailViewModelFactory(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            StarkExInteract starkExInteract

    ) {
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.findDefaultWalletInteract = findDefaultWalletInteract;
        this.starkExInteract = starkExInteract;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WawetCommandDetailViewModel(
                findDefaultNetworkInteract,
                findDefaultWalletInteract,
                starkExInteract);
    }
}
