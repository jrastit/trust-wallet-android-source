package com.aitivity.enterprise.wallet.aave;

import com.wallet.crypto.trustapp.entity.Wallet;

import io.reactivex.Single;

public interface ATokenRepositoryType {
    /*
    Observable<Transaction[]> fetchTransaction(Wallet wallet);
    Maybe<Transaction> findTransaction(Wallet wallet, String transactionHash);
    Single<String> createTransaction(Wallet from, String toAddress, BigInteger subunitAmount, BigInteger gasPrice, BigInteger gasLimit, byte[] data, String password);

     */
    Single<String> getExchangeRate(Wallet from);
    Single<String> getBalance(Wallet from);
}
