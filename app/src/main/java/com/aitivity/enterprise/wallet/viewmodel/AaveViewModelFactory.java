package com.aitivity.enterprise.wallet.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.aitivity.enterprise.wallet.interact.AaveInfoInteract;
import com.aitivity.enterprise.wallet.interact.GetAAVEBalance;
import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;
import com.wallet.crypto.trustapp.interact.GetDefaultWalletBalance;

import javax.inject.Inject;

public class AaveViewModelFactory implements ViewModelProvider.Factory {

    private final GetAAVEBalance getAAVEBalance;
    private final GetDefaultWalletBalance getDefaultWalletBalance;
    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private final FindDefaultWalletInteract findDefaultWalletInteract;
    private final AaveInfoInteract aaveInfoInteract;

    public AaveViewModelFactory(
            GetAAVEBalance getAAVEBalance,
            GetDefaultWalletBalance getDefaultWalletBalance,
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            AaveInfoInteract aaveInfoInteract) {
        this.getAAVEBalance = getAAVEBalance;
        this.getDefaultWalletBalance = getDefaultWalletBalance;
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.findDefaultWalletInteract = findDefaultWalletInteract;
        this.aaveInfoInteract = aaveInfoInteract;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AaveViewModel(
                    getAAVEBalance,
                    getDefaultWalletBalance,
                    findDefaultNetworkInteract,
                    findDefaultWalletInteract,
                    aaveInfoInteract);
    }
}
