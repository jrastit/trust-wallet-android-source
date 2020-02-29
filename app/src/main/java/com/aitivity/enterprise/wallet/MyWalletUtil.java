package com.aitivity.enterprise.wallet;

import com.wallet.crypto.trustapp.repository.EthereumNetworkRepositoryType;

import org.web3j.abi.datatypes.generated.Uint256;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MyWalletUtil {

    public static byte getChainId(EthereumNetworkRepositoryType networkRepository){
        return (new Integer(networkRepository.getDefaultNetwork().chainId)).byteValue();
    }

    public static BigInteger finalAmount(double amount){
        return (new BigDecimal(new BigInteger("1000000000000000000").doubleValue() * amount).toBigInteger());
    }

    public static String formatDecimal(String number, int decimal){
        int len = number.length();
        if (len > decimal){
            return number.substring(0, len - decimal) + "." + number.substring(len - decimal);
        }
        while (len < decimal){
            number = "0" + number;
            len = len + 1;
        }
        return "0." + number;
    }
}
