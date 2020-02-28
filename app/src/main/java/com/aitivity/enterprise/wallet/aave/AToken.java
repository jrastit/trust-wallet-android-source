package com.aitivity.enterprise.wallet.aave;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.1.0-android.
 */
public class AToken extends Contract {
    private static final String BINARY = "Bin file was not provided";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_INITIALEXCHANGERATE = "initialExchangeRate";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_UNDERLYINGASSETADDRESS = "underlyingAssetAddress";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_UNDERLYINGASSETDECIMALS = "underlyingAssetDecimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_REDEEM = "redeem";

    public static final String FUNC_MINTONDEPOSIT = "mintOnDeposit";

    public static final String FUNC_BURNONLIQUIDATION = "burnOnLiquidation";

    public static final String FUNC_TRANSFERONLIQUIDATION = "transferOnLiquidation";

    public static final String FUNC_GETEXCHANGERATE = "getExchangeRate";

    public static final String FUNC_BALANCEOFUNDERLYING = "balanceOfUnderlying";

    public static final String FUNC_ATOKENAMOUNTTOUNDERLYINGAMOUNT = "aTokenAmountToUnderlyingAmount";

    public static final String FUNC_ISTRANSFERALLOWED = "isTransferAllowed";

    public static final String FUNC_UNDERLYINGAMOUNTTOATOKENAMOUNT = "underlyingAmountToATokenAmount";

    public static final Event REDEEM_EVENT = new Event("Redeem", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event MINTONDEPOSIT_EVENT = new Event("MintOnDeposit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event BURNONLIQUIDATION_EVENT = new Event("BurnOnLiquidation", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFERONLIQUIDATION_EVENT = new Event("TransferOnLiquidation", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event BALANCETRANSFER_EVENT = new Event("BalanceTransfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected AToken(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AToken(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AToken(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Utf8String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> approve(Address spender, Uint256 value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(spender, value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> transferFrom(Address sender, Address recipient, Uint256 amount) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(sender, recipient, amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint8> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> increaseAllowance(Address spender, Uint256 addedValue) {
        final Function function = new Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(spender, addedValue), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> initialExchangeRate() {
        final Function function = new Function(FUNC_INITIALEXCHANGERATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> balanceOf(Address account) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(account), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> underlyingAssetAddress() {
        final Function function = new Function(FUNC_UNDERLYINGASSETADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Utf8String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> underlyingAssetDecimals() {
        final Function function = new Function(FUNC_UNDERLYINGASSETDECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> decreaseAllowance(Address spender, Uint256 subtractedValue) {
        final Function function = new Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(spender, subtractedValue), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transfer(Address recipient, Uint256 amount) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(recipient, amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> allowance(Address owner, Address spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(owner, spender), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public List<RedeemEventResponse> getRedeemEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REDEEM_EVENT, transactionReceipt);
        ArrayList<RedeemEventResponse> responses = new ArrayList<RedeemEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RedeemEventResponse typedResponse = new RedeemEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._underlyingValue = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._fromBalance = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RedeemEventResponse> redeemEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, RedeemEventResponse>() {
            @Override
            public RedeemEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REDEEM_EVENT, log);
                RedeemEventResponse typedResponse = new RedeemEventResponse();
                typedResponse.log = log;
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._underlyingValue = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._fromBalance = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<RedeemEventResponse> redeemEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REDEEM_EVENT));
        return redeemEventFlowable(filter);
    }

    public List<MintOnDepositEventResponse> getMintOnDepositEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MINTONDEPOSIT_EVENT, transactionReceipt);
        ArrayList<MintOnDepositEventResponse> responses = new ArrayList<MintOnDepositEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MintOnDepositEventResponse typedResponse = new MintOnDepositEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._underlyingValue = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._fromBalance = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MintOnDepositEventResponse> mintOnDepositEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, MintOnDepositEventResponse>() {
            @Override
            public MintOnDepositEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MINTONDEPOSIT_EVENT, log);
                MintOnDepositEventResponse typedResponse = new MintOnDepositEventResponse();
                typedResponse.log = log;
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._underlyingValue = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._fromBalance = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<MintOnDepositEventResponse> mintOnDepositEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINTONDEPOSIT_EVENT));
        return mintOnDepositEventFlowable(filter);
    }

    public List<BurnOnLiquidationEventResponse> getBurnOnLiquidationEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BURNONLIQUIDATION_EVENT, transactionReceipt);
        ArrayList<BurnOnLiquidationEventResponse> responses = new ArrayList<BurnOnLiquidationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BurnOnLiquidationEventResponse typedResponse = new BurnOnLiquidationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._underlyingValue = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._fromBalance = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BurnOnLiquidationEventResponse> burnOnLiquidationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, BurnOnLiquidationEventResponse>() {
            @Override
            public BurnOnLiquidationEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BURNONLIQUIDATION_EVENT, log);
                BurnOnLiquidationEventResponse typedResponse = new BurnOnLiquidationEventResponse();
                typedResponse.log = log;
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._underlyingValue = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._fromBalance = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<BurnOnLiquidationEventResponse> burnOnLiquidationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BURNONLIQUIDATION_EVENT));
        return burnOnLiquidationEventFlowable(filter);
    }

    public List<TransferOnLiquidationEventResponse> getTransferOnLiquidationEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFERONLIQUIDATION_EVENT, transactionReceipt);
        ArrayList<TransferOnLiquidationEventResponse> responses = new ArrayList<TransferOnLiquidationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferOnLiquidationEventResponse typedResponse = new TransferOnLiquidationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._underlyingValue = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._fromBalance = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse._toBalance = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferOnLiquidationEventResponse> transferOnLiquidationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferOnLiquidationEventResponse>() {
            @Override
            public TransferOnLiquidationEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFERONLIQUIDATION_EVENT, log);
                TransferOnLiquidationEventResponse typedResponse = new TransferOnLiquidationEventResponse();
                typedResponse.log = log;
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._underlyingValue = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._fromBalance = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse._toBalance = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public Flowable<TransferOnLiquidationEventResponse> transferOnLiquidationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFERONLIQUIDATION_EVENT));
        return transferOnLiquidationEventFlowable(filter);
    }

    public List<BalanceTransferEventResponse> getBalanceTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BALANCETRANSFER_EVENT, transactionReceipt);
        ArrayList<BalanceTransferEventResponse> responses = new ArrayList<BalanceTransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BalanceTransferEventResponse typedResponse = new BalanceTransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._underlyingValue = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._fromBalance = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse._toBalance = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BalanceTransferEventResponse> balanceTransferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, BalanceTransferEventResponse>() {
            @Override
            public BalanceTransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BALANCETRANSFER_EVENT, log);
                BalanceTransferEventResponse typedResponse = new BalanceTransferEventResponse();
                typedResponse.log = log;
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._underlyingValue = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._fromBalance = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse._toBalance = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public Flowable<BalanceTransferEventResponse> balanceTransferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BALANCETRANSFER_EVENT));
        return balanceTransferEventFlowable(filter);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.to = (Address) eventValues.getIndexedValues().get(1);
            typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.to = (Address) eventValues.getIndexedValues().get(1);
                typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.spender = (Address) eventValues.getIndexedValues().get(1);
            typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.spender = (Address) eventValues.getIndexedValues().get(1);
                typedResponse.value = (Uint256) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    public RemoteCall<TransactionReceipt> redeem(Uint256 _amount) {
        final Function function = new Function(
                FUNC_REDEEM, 
                Arrays.<Type>asList(_amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> mintOnDeposit(Address _account, Uint256 _underlyingAmount) {
        final Function function = new Function(
                FUNC_MINTONDEPOSIT, 
                Arrays.<Type>asList(_account, _underlyingAmount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> burnOnLiquidation(Address account, Uint256 value) {
        final Function function = new Function(
                FUNC_BURNONLIQUIDATION, 
                Arrays.<Type>asList(account, value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOnLiquidation(Address from, Address to, Uint256 value) {
        final Function function = new Function(
                FUNC_TRANSFERONLIQUIDATION, 
                Arrays.<Type>asList(from, to, value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getExchangeRate() {
        final Function function = new Function(FUNC_GETEXCHANGERATE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> balanceOfUnderlying(Address _user) {
        final Function function = new Function(FUNC_BALANCEOFUNDERLYING, 
                Arrays.<Type>asList(_user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> aTokenAmountToUnderlyingAmount(Uint256 _amount) {
        final Function function = new Function(FUNC_ATOKENAMOUNTTOUNDERLYINGAMOUNT, 
                Arrays.<Type>asList(_amount), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> isTransferAllowed(Address _from, Uint256 _amount) {
        final Function function = new Function(FUNC_ISTRANSFERALLOWED, 
                Arrays.<Type>asList(_from, _amount), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> underlyingAmountToATokenAmount(Uint256 _amount) {
        final Function function = new Function(FUNC_UNDERLYINGAMOUNTTOATOKENAMOUNT, 
                Arrays.<Type>asList(_amount), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    @Deprecated
    public static AToken load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AToken(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AToken(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AToken load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AToken(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AToken load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AToken(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class RedeemEventResponse {
        public Log log;

        public Address _from;

        public Uint256 _value;

        public Uint256 _underlyingValue;

        public Uint256 _fromBalance;
    }

    public static class MintOnDepositEventResponse {
        public Log log;

        public Address _from;

        public Uint256 _value;

        public Uint256 _underlyingValue;

        public Uint256 _fromBalance;
    }

    public static class BurnOnLiquidationEventResponse {
        public Log log;

        public Address _from;

        public Uint256 _value;

        public Uint256 _underlyingValue;

        public Uint256 _fromBalance;
    }

    public static class TransferOnLiquidationEventResponse {
        public Log log;

        public Address _from;

        public Address _to;

        public Uint256 _value;

        public Uint256 _underlyingValue;

        public Uint256 _fromBalance;

        public Uint256 _toBalance;
    }

    public static class BalanceTransferEventResponse {
        public Log log;

        public Address _from;

        public Address _to;

        public Uint256 _value;

        public Uint256 _underlyingValue;

        public Uint256 _fromBalance;

        public Uint256 _toBalance;
    }

    public static class TransferEventResponse {
        public Log log;

        public Address from;

        public Address to;

        public Uint256 value;
    }

    public static class ApprovalEventResponse {
        public Log log;

        public Address owner;

        public Address spender;

        public Uint256 value;
    }
}
