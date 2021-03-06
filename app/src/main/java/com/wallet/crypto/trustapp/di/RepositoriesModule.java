package com.wallet.crypto.trustapp.di;

import android.content.Context;

import com.aitivity.enterprise.wallet.repository.ATokenRepository;
import com.aitivity.enterprise.wallet.repository.ATokenRepositoryType;
import com.aitivity.enterprise.wallet.repository.ENSTestRepository;
import com.aitivity.enterprise.wallet.repository.ENSTestRepositoryType;
import com.aitivity.enterprise.wallet.repository.LendingPoolRepository;
import com.aitivity.enterprise.wallet.repository.LendingPoolRepositoryType;
import com.aitivity.enterprise.wallet.repository.StarkExRepositoryType;
import com.aitivity.enterprise.wallet.repository.StarkExRepository;
import com.aitivity.enterprise.wallet.service.WawetAPIService;
import com.aitivity.enterprise.wallet.service.WawetCommandService;
import com.google.gson.Gson;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.EthereumNetworkRepository;
import com.wallet.crypto.trustapp.repository.EthereumNetworkRepositoryType;
import com.wallet.crypto.trustapp.repository.PreferenceRepositoryType;
import com.wallet.crypto.trustapp.repository.RealmTokenSource;
import com.wallet.crypto.trustapp.repository.SharedPreferenceRepository;
import com.wallet.crypto.trustapp.repository.TokenLocalSource;
import com.wallet.crypto.trustapp.repository.TokenRepository;
import com.wallet.crypto.trustapp.repository.TokenRepositoryType;
import com.wallet.crypto.trustapp.repository.TransactionInMemorySource;
import com.wallet.crypto.trustapp.repository.TransactionLocalSource;
import com.wallet.crypto.trustapp.repository.TransactionRepository;
import com.wallet.crypto.trustapp.repository.TransactionRepositoryType;
import com.wallet.crypto.trustapp.repository.WalletRepository;
import com.wallet.crypto.trustapp.repository.WalletRepositoryType;
import com.wallet.crypto.trustapp.service.AccountKeystoreService;
import com.wallet.crypto.trustapp.service.BlockExplorerClient;
import com.wallet.crypto.trustapp.service.BlockExplorerClientType;
import com.wallet.crypto.trustapp.service.EthplorerTokenService;
import com.wallet.crypto.trustapp.service.GethKeystoreAccountService;
import com.wallet.crypto.trustapp.service.TickerService;
import com.wallet.crypto.trustapp.service.TokenExplorerClientType;
import com.wallet.crypto.trustapp.service.TrustWalletTickerService;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Single;
import okhttp3.OkHttpClient;

@Module
public class RepositoriesModule {
	@Singleton
	@Provides
	PreferenceRepositoryType providePreferenceRepository(Context context) {
		return new SharedPreferenceRepository(context);
	}

	@Singleton
	@Provides
	AccountKeystoreService provideAccountKeyStoreService(Context context) {
        File file = new File(context.getFilesDir(), "keystore/keystore");
		return new GethKeystoreAccountService(file);
	}

	@Singleton
    @Provides
    TickerService provideTickerService(OkHttpClient httpClient, Gson gson) {
	    return new TrustWalletTickerService(httpClient, gson);
    }

	@Singleton
	@Provides
	WawetCommandService provideWawetCommandService(OkHttpClient httpClient, Gson gson) {
		return new WawetAPIService(httpClient, gson);
	}

	@Singleton
	@Provides
	EthereumNetworkRepositoryType provideEthereumNetworkRepository(
            PreferenceRepositoryType preferenceRepository,
            TickerService tickerService) {
		return new EthereumNetworkRepository(preferenceRepository, tickerService);
	}

	@Singleton
	@Provides
    WalletRepositoryType provideWalletRepository(
            OkHttpClient okHttpClient,
			PreferenceRepositoryType preferenceRepositoryType,
			AccountKeystoreService accountKeystoreService,
			EthereumNetworkRepositoryType networkRepository,
			WawetCommandService wawetCommandService) {
		return new WalletRepository(
		        okHttpClient,
				preferenceRepositoryType,
				accountKeystoreService,
				networkRepository,
				wawetCommandService);
	}

	@Singleton
	@Provides
	TransactionRepositoryType provideTransactionRepository(
			EthereumNetworkRepositoryType networkRepository,
			AccountKeystoreService accountKeystoreService,
			BlockExplorerClientType blockExplorerClient) {
		TransactionLocalSource inMemoryCache = new TransactionInMemorySource();
		TransactionLocalSource inDiskCache = null;
		return new TransactionRepository(
				networkRepository,
				accountKeystoreService,
				inMemoryCache,
				inDiskCache,
				blockExplorerClient);
	}

	@Singleton
	@Provides
	ATokenRepositoryType provideATokenRepository(
			OkHttpClient okHttpClient,
			EthereumNetworkRepositoryType networkRepository,
			AccountKeystoreService accountKeystoreService,
			BlockExplorerClientType blockExplorerClient) {
		TransactionLocalSource inMemoryCache = new TransactionInMemorySource();
		return new ATokenRepository(
				okHttpClient,
				networkRepository,
				accountKeystoreService,
				inMemoryCache,
				blockExplorerClient);
	}

	@Singleton
	@Provides
	StarkExRepositoryType provideStarkExRepository(
			OkHttpClient okHttpClient,
			EthereumNetworkRepositoryType networkRepository,
			AccountKeystoreService accountKeystoreService,
			BlockExplorerClientType blockExplorerClient) {
		TransactionLocalSource inMemoryCache = new TransactionInMemorySource();
		return new StarkExRepository(
				okHttpClient,
				networkRepository,
				accountKeystoreService,
				inMemoryCache,
				blockExplorerClient);
	}

	@Singleton
	@Provides
	ENSTestRepositoryType provideENSTestRepository(
			OkHttpClient okHttpClient,
			EthereumNetworkRepositoryType networkRepository,
			AccountKeystoreService accountKeystoreService,
			BlockExplorerClientType blockExplorerClient) {
		TransactionLocalSource inMemoryCache = new TransactionInMemorySource();
		return new ENSTestRepository(
				okHttpClient,
				networkRepository,
				accountKeystoreService,
				inMemoryCache,
				blockExplorerClient);
	}

	@Singleton
	@Provides
	LendingPoolRepositoryType provideLendingPoolRepository(
			OkHttpClient okHttpClient,
			EthereumNetworkRepositoryType networkRepository,
			AccountKeystoreService accountKeystoreService,
			BlockExplorerClientType blockExplorerClient) {
		TransactionLocalSource inMemoryCache = new TransactionInMemorySource();
		return new LendingPoolRepository(
				okHttpClient,
				networkRepository,
				accountKeystoreService,
				inMemoryCache,
				blockExplorerClient);
	}

	@Singleton
	@Provides
	BlockExplorerClientType provideBlockExplorerClient(
			OkHttpClient httpClient,
			Gson gson,
			EthereumNetworkRepositoryType ethereumNetworkRepository) {
		return new BlockExplorerClient(httpClient, gson, ethereumNetworkRepository);
	}

	@Singleton
    @Provides
    TokenRepositoryType provideTokenRepository(
            OkHttpClient okHttpClient,
            EthereumNetworkRepositoryType ethereumNetworkRepository,
            TokenExplorerClientType tokenExplorerClientType,
            TokenLocalSource tokenLocalSource) {
	    return new TokenRepository(
	            okHttpClient,
	            ethereumNetworkRepository,
	            tokenExplorerClientType,
                tokenLocalSource);
    }

	@Singleton
    @Provides
    TokenExplorerClientType provideTokenService(OkHttpClient okHttpClient, Gson gson) {
	    return new EthplorerTokenService(okHttpClient, gson);
    }

    @Singleton
    @Provides
    TokenLocalSource provideRealmTokenSource() {
	    return new RealmTokenSource();
    }
}
