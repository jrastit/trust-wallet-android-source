package com.aitivity.enterprise.wallet.repository;

import com.aitivity.enterprise.wallet.MyTransactionManager;
import com.aitivity.enterprise.wallet.MyWalletUtil;
import com.aitivity.enterprise.wallet.smartContract.LendingPoolWeb3;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.EthereumNetworkRepositoryType;
import com.wallet.crypto.trustapp.repository.TransactionLocalSource;
import com.wallet.crypto.trustapp.service.AccountKeystoreService;
import com.wallet.crypto.trustapp.service.BlockExplorerClientType;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class LendingPoolRepository implements LendingPoolRepositoryType {

    private final EthereumNetworkRepositoryType networkRepository;
    private final AccountKeystoreService accountKeystoreService;
    private final TransactionLocalSource transactionLocalSource;
    private final BlockExplorerClientType blockExplorerClient;
    private final OkHttpClient httpClient;
    private String networkName;

    public LendingPoolRepository(OkHttpClient okHttpClient,
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
                return new BigInteger("2");
            }

            @Override
            public BigInteger getGasPrice() {
                return new BigInteger("2");
            }

            @Override
            public BigInteger getGasLimit(String contractFunc) {
                return new BigInteger("300000");
            }

            @Override
            public BigInteger getGasLimit() {
                return new BigInteger("300000");
            }
        };
    }

    private String getContractAddress(){

        if (networkName != null) {
            if (networkName.startsWith("Ropsten")) {
                return "0x9E5C7835E4b13368fd628196C4f1c6cEc89673Fa";
            }
            if (networkName.startsWith("Kovan")) {
                return "0x580D4Fdc4BF8f9b5ae2fb9225D584fED4AD5375c";
            }
        }

        return networkName;

    }

    @Override
    public Single<String> deposit(Wallet from, double amount, String password) {
        TransactionManager transactionManager = new MyTransactionManager(web3j,
                accountKeystoreService,
                from,
                password,
                MyWalletUtil.getChainId(networkRepository));
        LendingPoolWeb3 lendingPoolWeb3 = new LendingPoolWeb3(web3j,
                getContractAddress(),
                transactionManager,
                contractGasProvider,
                networkRepository,
                accountKeystoreService,
                transactionLocalSource,
                blockExplorerClient);
        return Single.fromCallable(() -> {
            TransactionReceipt tr = lendingPoolWeb3.deposit(
                    new Address("0xEeeeeEeeeEeEeeEeEeEeeEEEeeeeEeeeeeeeEEeE"),
                    new Uint256(MyWalletUtil.finalAmount(amount)),
                    new Uint16(0),
                    MyWalletUtil.finalAmount(amount))
                    .send();
            return tr.getTransactionHash();
        }).subscribeOn(Schedulers.io());
    }


}
