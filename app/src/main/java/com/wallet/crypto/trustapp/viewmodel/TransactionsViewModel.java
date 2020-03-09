package com.wallet.crypto.trustapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.net.Uri;

import com.aitivity.enterprise.wallet.entity.WawetCommand;
import com.aitivity.enterprise.wallet.interact.ENSTestInteract;
import com.aitivity.enterprise.wallet.interact.FetchWawetCommandInteract;
import com.aitivity.enterprise.wallet.interact.GetAAVEBalance;
import com.aitivity.enterprise.wallet.interact.StarkExInteract;
import com.aitivity.enterprise.wallet.router.ENSTestRouter;
import com.aitivity.enterprise.wallet.router.WawetCommandDetailRouter;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Transaction;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.interact.FetchTransactionsInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;
import com.wallet.crypto.trustapp.interact.GetDefaultWalletBalance;
import com.aitivity.enterprise.wallet.router.AaveRouter;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.router.ManageWalletsRouter;
import com.wallet.crypto.trustapp.router.MyAddressRouter;
import com.wallet.crypto.trustapp.router.MyTokensRouter;
import com.wallet.crypto.trustapp.router.SendRouter;
import com.wallet.crypto.trustapp.router.SettingsRouter;
import com.wallet.crypto.trustapp.router.TransactionDetailRouter;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class TransactionsViewModel extends BaseViewModel {
    private static final long GET_BALANCE_INTERVAL = 8;
    private static final long FETCH_TRANSACTIONS_INTERVAL = 10;
    private final MutableLiveData<NetworkInfo> defaultNetwork = new MutableLiveData<>();
    private final MutableLiveData<Wallet> defaultWallet = new MutableLiveData<>();
    private final MutableLiveData<Transaction[]> transactions = new MutableLiveData<>();
    private final MutableLiveData<WawetCommand[]> wawetCommand = new MutableLiveData<>();
    private final MutableLiveData<String> ensRegister = new MutableLiveData<>();
    private final MutableLiveData<Map<String, String>> defaultWalletBalance = new MutableLiveData<>();
    private final MutableLiveData<Map<String, String>> aaveBalance = new MutableLiveData<>();


    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private final FindDefaultWalletInteract findDefaultWalletInteract;
    private final GetDefaultWalletBalance getDefaultWalletBalance;
    private final GetAAVEBalance getAAVEBalance;
    private final FetchTransactionsInteract fetchTransactionsInteract;
    private final FetchWawetCommandInteract fetchWawetCommandInteract;

    private final ManageWalletsRouter manageWalletsRouter;
    private final SettingsRouter settingsRouter;
    private final AaveRouter aaveRouter;
    private final ENSTestRouter eNSTestRouter;
    private final SendRouter sendRouter;
    private final TransactionDetailRouter transactionDetailRouter;
    private final WawetCommandDetailRouter wawetCommandDetailRouter;
    private final MyAddressRouter myAddressRouter;
    private final MyTokensRouter myTokensRouter;
    private final ExternalBrowserRouter externalBrowserRouter;
    private Disposable balanceDisposable;
    private Disposable transactionDisposable;
    private Disposable wawetCommandDisposable;
    private final ENSTestInteract ensTestInteract;
    private final StarkExInteract starkExInteract;

    TransactionsViewModel(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            FetchTransactionsInteract fetchTransactionsInteract,
            FetchWawetCommandInteract fetchWawetCommandInteract,
            GetDefaultWalletBalance getDefaultWalletBalance,
            GetAAVEBalance getAAVEBalance,
            ENSTestInteract ensTestInteract,
            StarkExInteract starkExInteract,
            ManageWalletsRouter manageWalletsRouter,
            SettingsRouter settingsRouter,
            AaveRouter aaveRouter,
            ENSTestRouter eNSTestRouter,
            SendRouter sendRouter,
            TransactionDetailRouter transactionDetailRouter,
            WawetCommandDetailRouter wawetCommandDetailRouter,
            MyAddressRouter myAddressRouter,
            MyTokensRouter myTokensRouter,
            ExternalBrowserRouter externalBrowserRouter) {
        this.findDefaultNetworkInteract = findDefaultNetworkInteract;
        this.findDefaultWalletInteract = findDefaultWalletInteract;
        this.getDefaultWalletBalance = getDefaultWalletBalance;
        this.getAAVEBalance = getAAVEBalance;
        this.ensTestInteract = ensTestInteract;
        this.starkExInteract = starkExInteract;
        this.fetchTransactionsInteract = fetchTransactionsInteract;
        this.fetchWawetCommandInteract = fetchWawetCommandInteract;
        this.manageWalletsRouter = manageWalletsRouter;
        this.settingsRouter = settingsRouter;
        this.aaveRouter = aaveRouter;
        this.eNSTestRouter = eNSTestRouter;
        this.sendRouter = sendRouter;
        this.transactionDetailRouter = transactionDetailRouter;
        this.wawetCommandDetailRouter = wawetCommandDetailRouter;
        this.myAddressRouter = myAddressRouter;
        this.myTokensRouter = myTokensRouter;
        this.externalBrowserRouter = externalBrowserRouter;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (transactionDisposable != null)
            transactionDisposable.dispose();
        if (balanceDisposable != null)
            balanceDisposable.dispose();
    }

    public LiveData<NetworkInfo> defaultNetwork() {
        return defaultNetwork;
    }

    public LiveData<Wallet> defaultWallet() {
        return defaultWallet;
    }

    public LiveData<Transaction[]> transactions() {
        return transactions;
    }

    public LiveData<WawetCommand[]> wawetCommand() {
        return wawetCommand;
    }

    public LiveData<Map<String, String>> defaultWalletBalance() {
        return defaultWalletBalance;
    }

    public LiveData<Map<String, String>> aaveBalance() {
        return aaveBalance;
    }

    public LiveData<String> ensRegister(){ return ensRegister;}

    public void prepare() {
        progress.postValue(true);
        disposable = findDefaultNetworkInteract
                .find()
                .subscribe(this::onDefaultNetwork, this::onError);
    }

    public void fetchTransactions() {
        progress.postValue(true);
        transactionDisposable = Observable.interval(0, FETCH_TRANSACTIONS_INTERVAL, TimeUnit.SECONDS)
            .doOnNext(l ->
                disposable = fetchTransactionsInteract
                        .fetch(defaultWallet.getValue()/*new Wallet("0x60f7a1cbc59470b74b1df20b133700ec381f15d3")*/)
                        .subscribe(this::onTransactions, this::onError))
            .subscribe();
    }

    public void fetchWawetCommand() {
        progress.postValue(true);
        wawetCommandDisposable = Observable.interval(0, FETCH_TRANSACTIONS_INTERVAL, TimeUnit.SECONDS)
                .doOnNext(l ->
                        disposable = fetchWawetCommandInteract
                                .fetch().subscribe(this::onWawetCommand, this::onError)
                                )
                .subscribe();
        /*
        wawetCommandDisposable = Observable.interval(0, FETCH_TRANSACTIONS_INTERVAL, TimeUnit.SECONDS)
                .doOnNext(l ->
                        disposable = fetchWawetCommandInteract
                                .fetch()
                                .subscribe(this::onWawetCommand, this::onError))
                .subscribe();

         */
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

    private void onDefaultNetwork(NetworkInfo networkInfo) {
        defaultNetwork.postValue(networkInfo);
        disposable = findDefaultWalletInteract
                .find()
                .subscribe(this::onDefaultWallet, this::onError);
    }

    public void ensRegister(String name) {
        progress.postValue(true);
        disposable = ensTestInteract
                .register(defaultWallet.getValue(), defaultWallet.getValue().address, name)
                .subscribe(this::onRegister, this::onError);
    }

    private void onDefaultWallet(Wallet wallet) {
        defaultWallet.setValue(wallet);
        getBalance();
        getBalanceAAVE(wallet);
        fetchWawetCommand();

        //Don't work
        //fetchTransactions();
        progress.postValue(false);
    }

    private void onRegister(String status) {
        progress.postValue(false);
        this.ensRegister.postValue(status);
    }

    private void onTransactions(Transaction[] transactions) {
        progress.postValue(false);
        this.transactions.postValue(transactions);
    }

    private void onWawetCommand(WawetCommand[] wawetCommand) {
        progress.postValue(false);
        this.wawetCommand.postValue(wawetCommand);
    }

    public void showWallets(Context context) {
        manageWalletsRouter.open(context, false);
    }

    public void showSettings(Context context) {
        settingsRouter.open(context);
    }

    public void showAave(Context context) {
        aaveRouter.open(context);
    }

    public void showSend(Context context) { sendRouter.open(context); }

    public void showDetails(Context context, Transaction transaction) {
        transactionDetailRouter.open(context, transaction);
    }

    public void showWawetCommandDetails(Context context, WawetCommand wawetCommand) {
        wawetCommandDetailRouter.open(context, wawetCommand);
    }

    public void showMyAddress(Context context) {
        myAddressRouter.open(context, defaultWallet.getValue());
    }

    public void showTokens(Context context) {
        myTokensRouter.open(context, defaultWallet.getValue());
    }

    public void openDeposit(Context context, Uri uri) {
        externalBrowserRouter.open(context, uri);
    }
}
