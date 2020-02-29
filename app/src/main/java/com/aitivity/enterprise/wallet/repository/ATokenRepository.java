package com.aitivity.enterprise.wallet.repository;

import com.aitivity.enterprise.wallet.smartContract.ATokenWeb3;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.EthereumNetworkRepositoryType;
import com.wallet.crypto.trustapp.repository.TransactionLocalSource;
import com.wallet.crypto.trustapp.service.AccountKeystoreService;
import com.wallet.crypto.trustapp.service.BlockExplorerClientType;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;

import org.web3j.protocol.http.HttpService;

import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class ATokenRepository implements ATokenRepositoryType {

    final String kovan_addresse = "0x436264Ac032f7f271934Fa920dcD655210193090";
    final String ropsten_addresse = "0x2433A1b6FcF156956599280C3Eb1863247CFE675";

    private final EthereumNetworkRepositoryType networkRepository;
    private final AccountKeystoreService accountKeystoreService;
    private final TransactionLocalSource transactionLocalSource;
    private final BlockExplorerClientType blockExplorerClient;
    private final OkHttpClient httpClient;

    public ATokenRepository(OkHttpClient okHttpClient,
                            EthereumNetworkRepositoryType networkRepository,
                            AccountKeystoreService accountKeystoreService,
                            TransactionLocalSource inMemoryCache,
                            BlockExplorerClientType blockExplorerClient

    ){
        this.httpClient = okHttpClient;
        this.networkRepository = networkRepository;
        this.accountKeystoreService = accountKeystoreService;
        this.blockExplorerClient = blockExplorerClient;
        this.transactionLocalSource = inMemoryCache;

        this.networkRepository.addOnChangeDefaultNetwork(this::onNetworkChanged);

        this.networkRepository.addOnChangeDefaultNetwork(this::buildWeb3jClient);
        buildWeb3jClient(networkRepository.getDefaultNetwork());
    }

    private void onNetworkChanged(NetworkInfo networkInfo) {
        transactionLocalSource.clear();
    }

    private Web3j web3j;
    private ContractGasProvider contractGasProvider;

    private void buildWeb3jClient(NetworkInfo defaultNetwork) {
        web3j = Web3j.build(new HttpService(defaultNetwork.rpcServerUrl, httpClient, false));

        contractGasProvider = new ContractGasProvider() {
            @Override
            public BigInteger getGasPrice(String contractFunc) {
                return null;
            }

            @Override
            public BigInteger getGasPrice() {
                return null;
            }

            @Override
            public BigInteger getGasLimit(String contractFunc) {
                return null;
            }

            @Override
            public BigInteger getGasLimit() {
                return null;
            }
        };
    }

    public Single<String> getExchangeRate(Wallet from
              ){
        TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, from.address);

        ATokenWeb3 aTokenWeb3 = new ATokenWeb3(web3j, ropsten_addresse, transactionManager, contractGasProvider, networkRepository, accountKeystoreService, transactionLocalSource, blockExplorerClient);


        return Single.fromCallable(() -> {
            Uint256 ret = aTokenWeb3.totalSupply().send();
            //return ret.getValue().toString();
            return ret.getValue().toString();
        }).subscribeOn(Schedulers.io());

    }

    public Single<String> getBalance(Wallet from
    ){
        TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, from.address);

        ATokenWeb3 aTokenWeb3 = new ATokenWeb3(web3j, ropsten_addresse, transactionManager, contractGasProvider, networkRepository, accountKeystoreService, transactionLocalSource, blockExplorerClient);


        return Single.fromCallable(() -> {
            Uint256 ret = aTokenWeb3.balanceOf(new Address(from.address)).send();
            //Uint256 ret = aTokenWeb3.balanceOf(new Address("0x0")).send();
            return ret.getValue().toString();
            //return networkRepository.getDefaultNetwork().rpcServerUrl;
        }).subscribeOn(Schedulers.io());
    }

}
