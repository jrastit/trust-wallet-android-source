package com.wallet.crypto.trustapp.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.aitivity.enterprise.wallet.interact.ENSTestInteract;
import com.aitivity.enterprise.wallet.interact.FetchWawetCommandInteract;
import com.aitivity.enterprise.wallet.interact.GetAAVEBalance;
import com.aitivity.enterprise.wallet.interact.StarkExInteract;
import com.aitivity.enterprise.wallet.router.ENSTestRouter;
import com.aitivity.enterprise.wallet.router.WawetCommandDetailRouter;
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

public class TransactionsViewModelFactory implements ViewModelProvider.Factory {

    private final FindDefaultNetworkInteract findDefaultNetworkInteract;
    private final FindDefaultWalletInteract findDefaultWalletInteract;
    private final GetDefaultWalletBalance getDefaultWalletBalance;
    private final GetAAVEBalance getAAVEBalance;
    private final ENSTestInteract ensTestInteract;
    private final StarkExInteract starkExInteract;
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

    public TransactionsViewModelFactory(
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

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TransactionsViewModel(
                findDefaultNetworkInteract,
                findDefaultWalletInteract,
                fetchTransactionsInteract,
                fetchWawetCommandInteract,
                getDefaultWalletBalance,
                getAAVEBalance,
                ensTestInteract,
                starkExInteract,
                manageWalletsRouter,
                settingsRouter,
                aaveRouter,
                eNSTestRouter,
                sendRouter,
                transactionDetailRouter,
                wawetCommandDetailRouter,
                myAddressRouter,
                myTokensRouter,
                externalBrowserRouter);
    }
}
