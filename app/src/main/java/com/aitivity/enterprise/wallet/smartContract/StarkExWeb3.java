package com.aitivity.enterprise.wallet.smartContract;

import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.repository.EthereumNetworkRepositoryType;
import com.wallet.crypto.trustapp.repository.TransactionLocalSource;
import com.wallet.crypto.trustapp.service.AccountKeystoreService;
import com.wallet.crypto.trustapp.service.BlockExplorerClientType;

import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;


public class StarkExWeb3 extends StarkEx{

    private final EthereumNetworkRepositoryType networkRepository;
    private final AccountKeystoreService accountKeystoreService;
    private final TransactionLocalSource transactionLocalSource;
    private final BlockExplorerClientType blockExplorerClient;

    public StarkExWeb3(
            Web3j web3j,
            String address,
            TransactionManager transactionManager,
            ContractGasProvider contractGasProvider,
            EthereumNetworkRepositoryType networkRepository,
            AccountKeystoreService accountKeystoreService,
            TransactionLocalSource inMemoryCache,
            BlockExplorerClientType blockExplorerClient) {
        super(address, web3j, transactionManager, contractGasProvider);
        this.networkRepository = networkRepository;
        this.accountKeystoreService = accountKeystoreService;
        this.blockExplorerClient = blockExplorerClient;
        this.transactionLocalSource = inMemoryCache;
        this.networkRepository.addOnChangeDefaultNetwork(this::onNetworkChanged);
    }

    private void onNetworkChanged(NetworkInfo networkInfo) {
        transactionLocalSource.clear();
    }

}
