package com.aitivity.enterprise.wallet.repository;

import android.text.TextUtils;

import com.aitivity.enterprise.wallet.MyTransactionManager;
import com.aitivity.enterprise.wallet.MyWalletUtil;
import com.aitivity.enterprise.wallet.smartContract.ENSTestWeb3;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.EthereumNetworkRepositoryType;
import com.wallet.crypto.trustapp.repository.TransactionLocalSource;
import com.wallet.crypto.trustapp.service.AccountKeystoreService;
import com.wallet.crypto.trustapp.service.BlockExplorerClientType;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Collections;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class ENSTestRepository implements ENSTestRepositoryType {

    private final EthereumNetworkRepositoryType networkRepository;
    private final AccountKeystoreService accountKeystoreService;
    private final TransactionLocalSource transactionLocalSource;
    private final BlockExplorerClientType blockExplorerClient;
    private final OkHttpClient httpClient;
    private String networkName;

    public ENSTestRepository(OkHttpClient okHttpClient,
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
                return "0x09b5bd82f3351a4c8437fc6d7772a9e6cd5d25a1";
            }
        }

        return networkName;

    }

    public Single<String> getExpiryTimes(Wallet from, String name){
        TransactionManager transactionManager = new ReadonlyTransactionManager(web3j, from.address);

        ENSTestWeb3 ENSTestWeb3 = new ENSTestWeb3(
                web3j,
                getContractAddress(),
                transactionManager,
                contractGasProvider,
                networkRepository,
                accountKeystoreService,
                transactionLocalSource,
                blockExplorerClient);


        return Single.fromCallable(() -> {
            Uint256 ret = ENSTestWeb3.expiryTimes(new Bytes32(name.getBytes())).send();

            return ret.getValue().toString();
        }).subscribeOn(Schedulers.io());
    }

    // String to 64 length HexString (equivalent to 32 Hex lenght)
    public static String asciiToHex(String asciiValue)
    {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }

        return hex.toString() + TextUtils.join("", Collections.nCopies(32 - (hex.length()/2), "00"));
    }

    public Single<String> register(Wallet from, String addr, String name, String password){
        TransactionManager transactionManager = new MyTransactionManager(web3j,
                accountKeystoreService,
                from,
                password,
                MyWalletUtil.getChainId(networkRepository));

        ENSTestWeb3 ENSTestWeb3 = new ENSTestWeb3(
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

                String strInHex = asciiToHex(name);
                Bytes32 name32 = new Bytes32(Numeric.hexStringToByteArray(strInHex));

                TransactionReceipt tx = ENSTestWeb3.register(name32, new Address(addr)).send();

                return "Success: " + tx.getTransactionHash();
            }catch (Exception e){
                return "Error registrering : " + name + " : " + e.getMessage();
            }
        }).subscribeOn(Schedulers.io());
    }
}
