package com.aitivity.enterprise.wallet.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.aitivity.enterprise.wallet.interact.AaveInfoInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;

import javax.inject.Inject;

public class AaveViewModelFactory implements ViewModelProvider.Factory {

    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private FindDefaultWalletInteract findDefaultWalletInteract;
    private final AaveInfoInteract aaveInfoInteract;

    @Inject
    public AaveViewModelFactory(            FindDefaultNetworkInteract findDefaultNetworkInteract,
                                            FindDefaultWalletInteract findDefaultWalletInteract,
                                AaveInfoInteract aaveInfoInteract) {
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.findDefaultWalletInteract = findDefaultWalletInteract;
        this.aaveInfoInteract = aaveInfoInteract;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AaveViewModel(findDefaultNetworkInteract,
                    findDefaultWalletInteract,
                    aaveInfoInteract);
    }
}
