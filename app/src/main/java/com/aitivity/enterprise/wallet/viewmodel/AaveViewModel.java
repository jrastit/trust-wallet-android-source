package com.aitivity.enterprise.wallet.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.aitivity.enterprise.wallet.interact.AaveInfoInteract;
import com.aitivity.enterprise.wallet.interact.GetAAVEBalance;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;
import com.wallet.crypto.trustapp.interact.GetDefaultWalletBalance;
import com.wallet.crypto.trustapp.viewmodel.BaseViewModel;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class AaveViewModel extends BaseViewModel {

    private static final long GET_BALANCE_INTERVAL = 8;

    private final MutableLiveData<NetworkInfo> defaultNetwork = new MutableLiveData<>();
    private final MutableLiveData<Wallet> defaultWallet = new MutableLiveData<>();
    private final MutableLiveData<String> balance = new MutableLiveData<>();
    private final MutableLiveData<String> redeem = new MutableLiveData<>();
    private final MutableLiveData<String> test = new MutableLiveData<>();
    private final MutableLiveData<String> deposit = new MutableLiveData<>();
    private final MutableLiveData<Map<String, String>> aaveBalance = new MutableLiveData<>();
    private final MutableLiveData<Map<String, String>> defaultWalletBalance = new MutableLiveData<>();


    private final GetAAVEBalance getAAVEBalance;
    private final GetDefaultWalletBalance getDefaultWalletBalance;
    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private final FindDefaultWalletInteract findDefaultWalletInteract;
    private final AaveInfoInteract aaveInfoInteract;

    private Disposable balanceDisposable;

    public AaveViewModel(
            GetAAVEBalance getAAVEBalance,
            GetDefaultWalletBalance getDefaultWalletBalance,
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            AaveInfoInteract aaveInfoInteract) {
        this.getDefaultWalletBalance = getDefaultWalletBalance;
        this.getAAVEBalance = getAAVEBalance;
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.findDefaultWalletInteract = findDefaultWalletInteract;
        this.aaveInfoInteract = aaveInfoInteract;
    }



    public void getBalance(String from) {
        progress.postValue(true);
        disposable = aaveInfoInteract
                .getBalance(defaultWallet.getValue())
                .subscribe(this::onGetBalance, this::onError);
    }

    public void redeem(double value) {
        progress.postValue(true);
        disposable = aaveInfoInteract
                .redeem(defaultWallet.getValue(), value)
                .subscribe(this::onRedeem, this::onError);
    }


    public void test(String from) {
        progress.postValue(true);
        disposable = aaveInfoInteract
                .test(defaultWallet.getValue())
                .subscribe(this::onTest, this::onError);
    }

    public void deposit(double value) {
        progress.postValue(true);
        disposable = aaveInfoInteract
                .deposit(defaultWallet.getValue(), value)
                .subscribe(this::onDeposit, this::onError);
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

    public LiveData<String> balance() { return balance; }

    public LiveData<String> redeem() { return redeem; }

    public LiveData<String> test() { return test; }

    public LiveData<String> deposit() { return deposit; }

    public LiveData<Map<String, String>> aaveBalance() {
        return aaveBalance;
    }

    public LiveData<Map<String, String>> defaultWalletBalance() {
        return defaultWalletBalance;
    }

    private void onTest(String test) {
        progress.postValue(false);
        this.test.postValue(test);
    }

    private void onGetBalance(String balance) {
        progress.postValue(false);
        this.balance.postValue(balance);
    }

    private void onRedeem(String redeem) {
        progress.postValue(false);
        this.redeem.postValue(redeem);
    }

    private void onDeposit(String deposit) {
        progress.postValue(false);
        this.deposit.postValue(deposit);
    }

    private void onDefaultNetwork(NetworkInfo networkInfo) {
        defaultNetwork.postValue(networkInfo);
        disposable = findDefaultWalletInteract
                .find()
                .subscribe(this::onDefaultWallet, this::onError);
    }

    public void getBalanceAAVE(Wallet wallet) {
        balanceDisposable = Observable.interval(0, GET_BALANCE_INTERVAL, TimeUnit.SECONDS)
                .doOnNext(l -> getAAVEBalance.getBalance(defaultWallet.getValue()).subscribe(aaveBalance::postValue, t -> {}))
                .subscribe();
    }

    public void getBalance() {
        balanceDisposable = Observable.interval(0, GET_BALANCE_INTERVAL, TimeUnit.SECONDS)
                .doOnNext(l -> getDefaultWalletBalance
                        .get(defaultWallet.getValue())
                        .subscribe(defaultWalletBalance::postValue, t -> {}))
                .subscribe();
    }

    private void onDefaultWallet(Wallet wallet) {
        defaultWallet.setValue(wallet);
        getBalanceAAVE(wallet);
        getBalance();
    }

}
