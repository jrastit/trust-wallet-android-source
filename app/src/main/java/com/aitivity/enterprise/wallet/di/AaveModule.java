package com.aitivity.enterprise.wallet.di;


import com.aitivity.enterprise.wallet.interact.AaveInfoInteract;
import com.aitivity.enterprise.wallet.interact.GetAAVEBalance;
import com.aitivity.enterprise.wallet.viewmodel.AaveViewModelFactory;
import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;
import com.wallet.crypto.trustapp.interact.GetDefaultWalletBalance;
import com.wallet.crypto.trustapp.repository.EthereumNetworkRepositoryType;
import com.wallet.crypto.trustapp.repository.WalletRepositoryType;

import dagger.Module;
import dagger.Provides;

@Module
public class AaveModule {
    @Provides
    AaveViewModelFactory provideAaveViewModelFactory(
            GetAAVEBalance getAAVEBalance,
            GetDefaultWalletBalance getDefaultWalletBalance,
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            AaveInfoInteract aaveInfoInteract
    ){
        return new AaveViewModelFactory(
                getAAVEBalance,
                getDefaultWalletBalance,
                findDefaultNetworkInteract,
                findDefaultWalletInteract,
                aaveInfoInteract
        );
    }

    @Provides
    GetDefaultWalletBalance provideGetDefaultWalletBalance(
            WalletRepositoryType walletRepository, EthereumNetworkRepositoryType ethereumNetworkRepository) {
        return new GetDefaultWalletBalance(walletRepository, ethereumNetworkRepository);
    }

}
