package com.aitivity.enterprise.wallet.repository;

import com.aitivity.enterprise.wallet.MyTransactionManager;
import com.aitivity.enterprise.wallet.MyWalletUtil;
import com.aitivity.enterprise.wallet.smartContract.ATokenWeb3;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.EthereumNetworkRepositoryType;
import com.wallet.crypto.trustapp.repository.TransactionLocalSource;
import com.wallet.crypto.trustapp.service.AccountKeystoreService;
import com.wallet.crypto.trustapp.service.BlockExplorerClientType;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.protocol.Web3j;

import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class ATokenRepository implements ATokenRepositoryType {

    private final EthereumNetworkRepositoryType networkRepository;
    private final AccountKeystoreService accountKeystoreService;
    private final TransactionLocalSource transactionLocalSource;
    private final BlockExplorerClientType blockExplorerClient;
    private final OkHttpClient httpClient;
    private String networkName;

    public ATokenRepository(OkHttpClient okHttpClient,
                            EthereumNetworkRepositoryType networkRepository,
                            AccountKeystoreService accountKeystoreService,
                            TransactionLocalSource inMemoryCache,
                            BlockExplorerClientType blockExplorerClient

    ){
        this.networkName = null;
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
        networkName = defaultNetwork.name;

        contractGasProvider = new ContractGasProvider() {
            @Override
            public BigInteger getGasPrice(String contractFunc) {
                return new BigInteger("1");
            }

            @Override
            public BigInteger getGasPrice() {
                return new BigInteger("1");
            }

            @Override
            public BigInteger getGasLimit(String contractFunc) {
                return new BigInteger("3000000");
            }

            @Override
            public BigInteger getGasLimit() {
                return new BigInteger("3000000");
            }
        };
    }

    private String getContractAddress(){

        if (networkName != null) {
            if (networkName.startsWith("Ropsten")) {
                return "0x2433A1b6FcF156956599280C3Eb1863247CFE675";
            }
            if (networkName.startsWith("Kovan")) {
                return "0xD483B49F2d55D2c53D32bE6efF735cB001880F79";
            }
        }

        return networkName;

    }

    public Single<String> getBalance(Wallet from
    ){
        TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, from.address);

        ATokenWeb3 aTokenWeb3 = new ATokenWeb3(
                web3j,
                getContractAddress(),
                transactionManager,
                contractGasProvider,
                networkRepository,
                accountKeystoreService,
                transactionLocalSource,
                blockExplorerClient);


        return Single.fromCallable(() -> {
            Uint256 ret = aTokenWeb3.balanceOf(new Address(from.address)).send();

            return MyWalletUtil.formatDecimal(ret.getValue().toString(), 18);
        }).subscribeOn(Schedulers.io());
    }

    public Single<String> redeem(Wallet from, double amount, String password
    ){
        TransactionManager transactionManager = new MyTransactionManager(web3j, accountKeystoreService, from, password, MyWalletUtil.getChainId(networkRepository));

        ATokenWeb3 aTokenWeb3 = new ATokenWeb3(
                web3j,
                getContractAddress(),
                transactionManager,
                contractGasProvider,
                networkRepository,
                accountKeystoreService,
                transactionLocalSource,
                blockExplorerClient);

        return Single.fromCallable(() -> {
            try {
                TransactionReceipt tr = aTokenWeb3.redeem(new Uint256(MyWalletUtil.finalAmount(amount))).send();
                return "Success : " + tr.getTransactionHash();
            }catch (Exception e){
                return "Error : " + e.getMessage();
            }
        }).subscribeOn(Schedulers.io());
    }

    public Single<String> test(Wallet from, String password
    ){
        TransactionManager transactionManager = new MyTransactionManager(
                web3j,
                accountKeystoreService,
                from,
                password,
                MyWalletUtil.getChainId(networkRepository));

        ATokenWeb3 aTokenWeb3 = new ATokenWeb3(
                web3j,
                getContractAddress(),
                transactionManager,
                contractGasProvider,
                networkRepository,
                accountKeystoreService,
                transactionLocalSource,
                blockExplorerClient);

        return Single.fromCallable(() -> {
            Uint8 ret = aTokenWeb3.decimals().send();
            Uint256 ret2 = aTokenWeb3.principalBalanceOf(new Address(from.address)).send();
            return "decimal : " +
                    ret.getValue().toString() +
                    "\n" +
                    "Principale balance : " +
                    MyWalletUtil.formatDecimal(ret2.getValue().toString(), 18);
        }).subscribeOn(Schedulers.io());
    }
}
