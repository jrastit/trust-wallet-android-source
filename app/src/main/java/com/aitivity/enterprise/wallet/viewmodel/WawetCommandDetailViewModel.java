package com.aitivity.enterprise.wallet.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Transaction;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.viewmodel.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class WawetCommandDetailViewModel extends BaseViewModel {

    private final ExternalBrowserRouter externalBrowserRouter;

    WawetCommandDetailViewModel(ExternalBrowserRouter externalBrowserRouter) {
        this.externalBrowserRouter = externalBrowserRouter;
    }

}
