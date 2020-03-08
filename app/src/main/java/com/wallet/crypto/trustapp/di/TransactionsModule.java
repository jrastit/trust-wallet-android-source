package com.wallet.crypto.trustapp.di;

import com.aitivity.enterprise.wallet.interact.ENSTestInteract;
import com.aitivity.enterprise.wallet.interact.FetchWawetCommandInteract;
import com.aitivity.enterprise.wallet.interact.GetAAVEBalance;
import com.aitivity.enterprise.wallet.repository.ENSTestRepository;
import com.aitivity.enterprise.wallet.repository.ENSTestRepositoryType;
import com.aitivity.enterprise.wallet.router.ENSTestRouter;
import com.aitivity.enterprise.wallet.router.WawetCommandDetailRouter;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.interact.FetchTransactionsInteract;
import com.wallet.crypto.trustapp.interact.FetchWalletsInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultNetworkInteract;
import com.wallet.crypto.trustapp.interact.FindDefaultWalletInteract;
import com.wallet.crypto.trustapp.interact.GetDefaultWalletBalance;
import com.wallet.crypto.trustapp.repository.EthereumNetworkRepositoryType;
import com.wallet.crypto.trustapp.repository.PasswordStore;
import com.wallet.crypto.trustapp.repository.TransactionRepositoryType;
import com.wallet.crypto.trustapp.repository.WalletRepositoryType;
import com.aitivity.enterprise.wallet.router.AaveRouter;
import com.wallet.crypto.trustapp.router.ExternalBrowserRouter;
import com.wallet.crypto.trustapp.router.ManageWalletsRouter;
import com.wallet.crypto.trustapp.router.MyAddressRouter;
import com.wallet.crypto.trustapp.router.MyTokensRouter;
import com.wallet.crypto.trustapp.router.SendRouter;
import com.wallet.crypto.trustapp.router.SettingsRouter;
import com.wallet.crypto.trustapp.router.TransactionDetailRouter;
import com.wallet.crypto.trustapp.viewmodel.TransactionsViewModelFactory;

import dagger.Module;
import dagger.Provides;

@Module
class TransactionsModule {
    @Provides
    TransactionsViewModelFactory provideTransactionsViewModelFactory(
            FindDefaultNetworkInteract findDefaultNetworkInteract,
            FindDefaultWalletInteract findDefaultWalletInteract,
            FetchTransactionsInteract fetchTransactionsInteract,
            FetchWawetCommandInteract fetchWawetCommandInteract,
            GetDefaultWalletBalance getDefaultWalletBalance,
            GetAAVEBalance getAAVEBalance,
            ENSTestInteract ensTestInteract,
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
        return new TransactionsViewModelFactory(
                findDefaultNetworkInteract,
                findDefaultWalletInteract,
                fetchTransactionsInteract,
                fetchWawetCommandInteract,
                getDefaultWalletBalance,
                getAAVEBalance,
                ensTestInteract,
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

    @Provides
    FindDefaultNetworkInteract provideFindDefaultNetworkInteract(
            EthereumNetworkRepositoryType ethereumNetworkRepositoryType) {
        return new FindDefaultNetworkInteract(ethereumNetworkRepositoryType);
    }

    @Provides
    FindDefaultWalletInteract provideFindDefaultWalletInteract(WalletRepositoryType walletRepository) {
        return new FindDefaultWalletInteract(walletRepository);
    }

    @Provides
    FetchTransactionsInteract provideFetchTransactionsInteract(TransactionRepositoryType transactionRepository) {
        return new FetchTransactionsInteract(transactionRepository);
    }

    @Provides
    FetchWawetCommandInteract provideFetchWalletCommandInteract(WalletRepositoryType walletRepository) {
        return new FetchWawetCommandInteract(walletRepository);
    }

    @Provides
    GetDefaultWalletBalance provideGetDefaultWalletBalance(
            WalletRepositoryType walletRepository, EthereumNetworkRepositoryType ethereumNetworkRepository) {
        return new GetDefaultWalletBalance(walletRepository, ethereumNetworkRepository);
    }

    @Provides
    ENSTestInteract provideENSTestInteract(ENSTestRepositoryType eNSTestRepository,
                                           PasswordStore passwordStore){
        return new ENSTestInteract(eNSTestRepository, passwordStore);
    }

    @Provides
    ManageWalletsRouter provideManageWalletsRouter() {
        return new ManageWalletsRouter();
    }

    @Provides
    SettingsRouter provideSettingsRouter() {
        return new SettingsRouter();
    }

    @Provides
    AaveRouter provideAaveRouter() {
        return new AaveRouter();
    }

    @Provides
    ENSTestRouter provideENSTestRouter() {
        return new ENSTestRouter();
    }

    @Provides
    SendRouter provideSendRouter() { return new SendRouter(); }

    @Provides
    TransactionDetailRouter provideTransactionDetailRouter() {
        return new TransactionDetailRouter();
    }

    @Provides
    WawetCommandDetailRouter provideWawetCommandDetailRouter() {
        return new WawetCommandDetailRouter();
    }

    @Provides
    MyAddressRouter provideMyAddressRouter() {
        return new MyAddressRouter();
    }

    @Provides
    MyTokensRouter provideMyTokensRouter() {
        return new MyTokensRouter();
    }

    @Provides
    ExternalBrowserRouter provideExternalBrowserRouter() {
        return new ExternalBrowserRouter();
    }
}
