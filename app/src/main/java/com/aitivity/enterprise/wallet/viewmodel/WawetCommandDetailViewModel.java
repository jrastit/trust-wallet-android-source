package com.aitivity.enterprise.wallet.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.aitivity.enterprise.wallet.interact.StarkExInteract;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;
import com.wallet.crypto.trustapp.viewmodel.BaseViewModel;

public class WawetCommandDetailViewModel extends BaseViewModel {

    private final MutableLiveData<NetworkInfo> defaultNetwork = new MutableLiveData<>();
    private final MutableLiveData<Wallet> defaultWallet = new MutableLiveData<>();
    private final MutableLiveData<String> register = new MutableLiveData<>();

    private final StarkExInteract starkExInteract;
    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private final FindDefaultWalletInteract findDefaultWalletInteract;

    WawetCommandDetailViewModel(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            StarkExInteract starkExInteract
        ) {
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.findDefaultWalletInteract = findDefaultWalletInteract;
        this.starkExInteract = starkExInteract;
    }

    public void register(String starkKey, String starkSignature) {
        progress.postValue(true);
        disposable = starkExInteract
                .register(defaultWallet.getValue(), starkKey, starkSignature)
                .subscribe(this::onRegister, this::onError);
    }

    public LiveData<NetworkInfo> defaultNetwork() {
        return defaultNetwork;
    }

    public LiveData<String> register() {
        return register;
    }

    public LiveData<Wallet> defaultWallet() {
        return defaultWallet;
    }

    private void onRegister(String register) {
        progress.postValue(false);
        this.register.postValue(register);
    }

    public void prepare() {
        progress.postValue(true);
        disposable = findDefaultNetworkInteract
                .find()
                .subscribe(this::onDefaultNetwork, this::onError);
    }

    private void onDefaultNetwork(NetworkInfo networkInfo) {
        defaultNetwork.postValue(networkInfo);
        disposable = findDefaultWalletInteract
                .find()
                .subscribe(this::onDefaultWallet, this::onError);
    }

    private void onDefaultWallet(Wallet wallet) {
        defaultWallet.setValue(wallet);
        //getBalanceAAVE(wallet);
        //getBalance();
    }
}
