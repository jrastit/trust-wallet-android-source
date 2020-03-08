package com.aitivity.enterprise.wallet.di;

import com.aitivity.enterprise.wallet.viewmodel.WawetCommandDetailViewModelFactory;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;

import dagger.Module;
import dagger.Provides;

@Module
public class WawetCommandDetailModule {

    @Provides
    WawetCommandDetailViewModelFactory provideWawetCommandDetailViewModelFactory(
            ExternalBrowserRouter externalBrowserRouter) {
        return new WawetCommandDetailViewModelFactory(
                externalBrowserRouter);
    }

}
