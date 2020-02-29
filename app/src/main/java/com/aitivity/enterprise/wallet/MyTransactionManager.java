package com.aitivity.enterprise.wallet;

import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.service.AccountKeystoreService;

import org.ethereum.geth.Address;
import org.ethereum.geth.BigInt;
import org.web3j.crypto.Hash;
import org.ethereum.geth.Transaction;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.ChainId;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.exceptions.TxHashMismatchException;
import org.web3j.tx.response.TransactionReceiptProcessor;
import org.web3j.utils.Numeric;
import org.web3j.utils.TxHashVerifier;

import java.io.IOException;
import java.math.BigInteger;

public class MyTransactionManager extends TransactionManager {

    /**
     * TransactionManager implementation using Ethereum wallet file to create and sign transactions
     * locally.
     *
     * <p>This transaction manager provides support for specifying the chain id for transactions as per
     * <a href="https://github.com/ethereum/EIPs/issues/155">EIP155</a>.
     */

    private final Web3j web3j;
    //final Credentials credentials;
    final Wallet wallet;
    final String password;
    final AccountKeystoreService accountKeystoreService;


    private final byte chainId;

    protected TxHashVerifier txHashVerifier = new TxHashVerifier();


    public MyTransactionManager(
            Web3j web3j, AccountKeystoreService accountKeystoreService, Wallet wallet, String password, byte chainId,
            TransactionReceiptProcessor transactionReceiptProcessor) {
        super(transactionReceiptProcessor, wallet.address);
        this.web3j = web3j;
        this.wallet = wallet;
        this.password = password;
        this.chainId = chainId;
        this.accountKeystoreService = accountKeystoreService;
    }

    public MyTransactionManager(Web3j web3j, AccountKeystoreService accountKeystoreService, Wallet wallet, String password, byte chainId) {
        super(web3j, wallet.address);
        this.web3j = web3j;
        this.wallet = wallet;
        this.password = password;
        this.chainId = chainId;
        this.accountKeystoreService = accountKeystoreService;
    }

    public MyTransactionManager(
            Web3j web3j, AccountKeystoreService accountKeystoreService, Wallet wallet, String password, byte chainId, int attempts, long sleepDuration) {
        super(web3j, attempts, sleepDuration, wallet.address);
        this.web3j = web3j;
        this.wallet = wallet;
        this.password = password;
        this.chainId = chainId;
        this.accountKeystoreService = accountKeystoreService;
    }

    public MyTransactionManager(Web3j web3j, AccountKeystoreService accountKeystoreService, Wallet wallet, String password) {
        this(web3j, accountKeystoreService, wallet, password, ChainId.NONE);
    }

    public MyTransactionManager(Web3j web3j, AccountKeystoreService accountKeystoreService, Wallet wallet, String password, int attempts, int sleepDuration) {
        this(web3j, accountKeystoreService, wallet, password, ChainId.NONE, attempts, sleepDuration);
    }

    protected BigInteger getNonce() throws IOException {
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                wallet.address, DefaultBlockParameterName.PENDING).send();

        return ethGetTransactionCount.getTransactionCount();
    }

    public TxHashVerifier getTxHashVerifier() {
        return txHashVerifier;
    }

    public void setTxHashVerifier(TxHashVerifier txHashVerifier) {
        this.txHashVerifier = txHashVerifier;
    }

    @Override
    public EthSendTransaction sendTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger amount) throws IOException {

        BigInteger nonce = getNonce();

        BigInt value = new BigInt(0);
        value.setString(amount.toString(), 10);

        BigInt gasPriceBI = new BigInt(0);
        if (gasPrice != null) {
            gasPriceBI.setString(gasPrice.toString(), 10);
        }

        BigInt gasLimitBI = new BigInt(0);
        if (gasLimit != null) {
            gasLimitBI.setString(gasLimit.toString(), 10);
        }


        Transaction tx = new Transaction(
                nonce.longValue(),
                new Address(to),
                value,
                gasLimitBI,
                gasPriceBI,
                Numeric.hexStringToByteArray(Numeric.cleanHexPrefix(data)));

        return signAndSend(tx);
    }

    public EthSendTransaction signAndSend(Transaction transaction)
            throws IOException {

        byte[] signedMessage;

        if (chainId > ChainId.NONE) {
            signedMessage = accountKeystoreService.signTransactionBin(wallet, password, transaction, chainId);
        } else {
            signedMessage = accountKeystoreService.signTransactionBin(wallet, password, transaction, chainId);
        }

        String hexValue = Numeric.toHexString(signedMessage);
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction(hexValue).send();

        if (ethSendTransaction != null && !ethSendTransaction.hasError()) {
            String txHashLocal = Hash.sha3(hexValue);
            String txHashRemote = ethSendTransaction.getTransactionHash();
            if (!txHashVerifier.verify(txHashLocal, txHashRemote)) {
                throw new TxHashMismatchException(txHashLocal, txHashRemote);
            }
        }

        return ethSendTransaction;
    }


}
