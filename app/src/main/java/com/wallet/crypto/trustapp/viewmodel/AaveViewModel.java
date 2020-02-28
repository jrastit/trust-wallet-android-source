package com.wallet.crypto.trustapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.interact.AaveInfoInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;

public class AaveViewModel extends BaseViewModel {
    private final MutableLiveData<NetworkInfo> defaultNetwork = new MutableLiveData<>();
    private final MutableLiveData<Wallet> defaultWallet = new MutableLiveData<>();
    private final MutableLiveData<String> exchangeRate = new MutableLiveData<>();
    private final MutableLiveData<String> balance = new MutableLiveData<>();
    private final MutableLiveData<String> balanceDecoded = new MutableLiveData<>();

    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private final FindDefaultWalletInteract findDefaultWalletInteract;
    private final AaveInfoInteract aaveInfoInteract;

    public AaveViewModel(FindDefaultNetworkInteract findDefaultNetworkInteract,
                         FindDefaultWalletInteract findDefaultWalletInteract,
                         AaveInfoInteract aaveInfoInteract) {
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.findDefaultWalletInteract = findDefaultWalletInteract;
        this.aaveInfoInteract = aaveInfoInteract;
    }

    public void getExchangeRate(String from) {
        progress.postValue(true);
        disposable = aaveInfoInteract
                .getExchangeRate(defaultWallet.getValue())
                .subscribe(this::onGetExchangeRate, this::onError);
    }

    public void getBalance(String from) {
        progress.postValue(true);
        disposable = aaveInfoInteract
                .getBalance(defaultWallet.getValue())
                .subscribe(this::onGetBalance, this::onError);
    }

    public void prepare() {
        progress.postValue(true);
        disposable = findDefaultNetworkInteract
                .find()
                .subscribe(this::onDefaultNetwork, this::onError);
    }

    public LiveData<NetworkInfo> defaultNetwork() {
        return defaultNetwork;
    }

    public LiveData<Wallet> defaultWallet() {
        return defaultWallet;
    }

    public LiveData<String> getExchangeRate() { return exchangeRate; }

    public LiveData<String> getBalance() { return balance; }

    public LiveData<String> getBalanceDecoded() { return balanceDecoded; }

    private void onGetExchangeRate(String exchangeRate) {
        progress.postValue(false);
        this.exchangeRate.postValue(exchangeRate);
    }

    private void onGetBalance(String balance) {
        progress.postValue(false);
        this.balance.postValue(balance);
        if (this.balance.getValue() != null && this.exchangeRate.getValue() != null){
            Float balanceDecodedlong = (Float.valueOf(this.balance.getValue()) / Float.valueOf(this.exchangeRate.getValue()));
            this.balanceDecoded.postValue(balanceDecodedlong.toString());
        }

    }

    private void onDefaultNetwork(NetworkInfo networkInfo) {
        defaultNetwork.postValue(networkInfo);
        disposable = findDefaultWalletInteract
                .find()
                .subscribe(this::onDefaultWallet, this::onError);
    }

    private void onDefaultWallet(Wallet wallet) {
        defaultWallet.setValue(wallet);
    }


}
