package com.aitivity.enterprise.wallet.di;

import com.aitivity.enterprise.wallet.interact.StarkExInteract;
import com.aitivity.enterprise.wallet.viewmodel.WawetCommandDetailViewModelFactory;
import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;

import dagger.Module;
import dagger.Provides;

@Module
public class WawetCommandDetailModule {

    @Provides
    WawetCommandDetailViewModelFactory provideWawetCommandDetailViewModelFactory(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            StarkExInteract starkExInteract
            ) {
        return new WawetCommandDetailViewModelFactory(
                findDefaultNetworkInteract,
                findDefaultWalletInteract,
                starkExInteract);
    }

}
