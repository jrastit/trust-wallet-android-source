package com.aitivity.enterprise.wallet.repository;

import android.util.Log;

import com.aitivity.enterprise.wallet.MyTransactionManager;
import com.aitivity.enterprise.wallet.MyWalletUtil;
import com.aitivity.enterprise.wallet.smartContract.StarkExWeb3;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.repository.EthereumNetworkRepositoryType;
import com.wallet.crypto.trustapp.repository.TransactionLocalSource;
import com.wallet.crypto.trustapp.service.AccountKeystoreService;
import com.wallet.crypto.trustapp.service.BlockExplorerClientType;

import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;

public class StarkExRepository implements StarkExRepositoryType {

    private final String TAG = "StarkExRepository";

    private final EthereumNetworkRepositoryType networkRepository;
    private final AccountKeystoreService accountKeystoreService;
    private final TransactionLocalSource transactionLocalSource;
    private final BlockExplorerClientType blockExplorerClient;
    private final OkHttpClient httpClient;
    private String networkName;

    public StarkExRepository(OkHttpClient okHttpClient,
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
                return "0x204eAF71D3f15CF6F9A024159228573EE4543bF9";
            }
        }

        return networkName;

    }


    @Override
    public Single<String> register(Wallet from, String starkKey, String starkSignature, String password) {
        Log.d(TAG, "register: 1");
        TransactionManager transactionManager = new MyTransactionManager(web3j, accountKeystoreService, from, password, MyWalletUtil.getChainId(networkRepository));
        Log.d(TAG, "register: 2");
        StarkExWeb3 starkExWeb3 = new StarkExWeb3(
                web3j,
                getContractAddress(),
                transactionManager,
                contractGasProvider,
                networkRepository,
                accountKeystoreService,
                transactionLocalSource,
                blockExplorerClient);
        Log.d(TAG, "register: 3");
        return Single.fromCallable(() -> {
            try {
                //MyWalletUtil.finalAmount()
                //byte[] biteArray = Numeric.hexStringToByteArray(starkKey);
                //Uint256 starkKey256 = new Uint256(biteArray);
                Uint256 starkKey256 = new Uint256(new BigInteger(starkKey.substring(2), 16));
                DynamicBytes starkSignatureByte = new DynamicBytes(Numeric.hexStringToByteArray(starkSignature));
                TransactionReceipt tr = starkExWeb3.register(starkKey256, starkSignatureByte).send();
                return "Success : " + tr.getTransactionHash();
            }catch (Exception e){
                Log.e(TAG, "register: ", e);
                return "Error : " + e.getMessage();
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }


    @Override
    public Single<String> deposit(Wallet from, String tokenId, String vaultId, String amount, String password) {
        Log.i(TAG, "deposit: 1");
        TransactionManager transactionManager = new MyTransactionManager(web3j, accountKeystoreService, from, password, MyWalletUtil.getChainId(networkRepository));
        Log.i(TAG, "deposit: 2");
        StarkExWeb3 starkExWeb3 = new StarkExWeb3(
                web3j,
                getContractAddress(),
                transactionManager,
                contractGasProvider,
                networkRepository,
                accountKeystoreService,
                transactionLocalSource,
                blockExplorerClient);
        Log.i(TAG, "deposit: 3");
        return Single.fromCallable(() -> {
            try {
                //MyWalletUtil.finalAmount()
                //byte[] biteArray = Numeric.hexStringToByteArray(starkKey);
                //Uint256 starkKey256 = new Uint256(biteArray);
                Uint256 tokenId256 = new Uint256(new BigInteger(tokenId.substring(2), 16));
                Uint256 vaultId256 = new Uint256(new BigInteger(vaultId));
                BigInteger amountBigInt = new BigInteger(amount);
                Log.i(TAG, "deposit: " + amountBigInt);
                TransactionReceipt tr = starkExWeb3.deposit(tokenId256, vaultId256, amountBigInt).send();
                return "Success : " + tr.getTransactionHash();
            } catch (Exception e) {
                Log.e(TAG, "deposit: ", e);
                return "Error : " + e.getMessage();
            }
        }).observeOn(AndroidSchedulers.mainThread());
    }
}
