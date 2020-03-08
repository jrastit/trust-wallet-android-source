package com.aitivity.enterprise.wallet.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.viewmodel.TransactionDetailViewModel;

public class WawetCommandDetailViewModelFactory implements ViewModelProvider.Factory {

    private final ExternalBrowserRouter externalBrowserRouter;

    public WawetCommandDetailViewModelFactory(ExternalBrowserRouter externalBrowserRouter) {
        this.externalBrowserRouter = externalBrowserRouter;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WawetCommandDetailViewModel(
                externalBrowserRouter);
    }
}
