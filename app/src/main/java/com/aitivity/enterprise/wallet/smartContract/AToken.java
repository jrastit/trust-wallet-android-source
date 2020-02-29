package com.aitivity.enterprise.wallet.smartContract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
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

    public static final String FUNC_UINT_MAX_VALUE = "UINT_MAX_VALUE";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_UNDERLYINGASSETADDRESS = "underlyingAssetAddress";

    public static final String FUNC_REDIRECTINTERESTSTREAM = "redirectInterestStream";

    public static final String FUNC_REDIRECTINTERESTSTREAMOF = "redirectInterestStreamOf";

    public static final String FUNC_ALLOWINTERESTREDIRECTIONTO = "allowInterestRedirectionTo";

    public static final String FUNC_REDEEM = "redeem";

    public static final String FUNC_MINTONDEPOSIT = "mintOnDeposit";

    public static final String FUNC_BURNONLIQUIDATION = "burnOnLiquidation";

    public static final String FUNC_TRANSFERONLIQUIDATION = "transferOnLiquidation";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_PRINCIPALBALANCEOF = "principalBalanceOf";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_ISTRANSFERALLOWED = "isTransferAllowed";

    public static final String FUNC_GETUSERINDEX = "getUserIndex";

    public static final String FUNC_GETINTERESTREDIRECTIONADDRESS = "getInterestRedirectionAddress";

    public static final String FUNC_GETREDIRECTEDBALANCE = "getRedirectedBalance";

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event BALANCETRANSFER_EVENT = new Event("BalanceTransfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event BURNONLIQUIDATION_EVENT = new Event("BurnOnLiquidation", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event INTERESTREDIRECTIONALLOWANCECHANGED_EVENT = new Event("InterestRedirectionAllowanceChanged", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event INTERESTSTREAMREDIRECTED_EVENT = new Event("InterestStreamRedirected", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event MINTONDEPOSIT_EVENT = new Event("MintOnDeposit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REDEEM_EVENT = new Event("Redeem", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REDIRECTEDBALANCEUPDATED_EVENT = new Event("RedirectedBalanceUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
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
        return web3j.ethLogFlowable(filter).map(new Function<Log, ApprovalEventResponse>() {
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

    public List<BalanceTransferEventResponse> getBalanceTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BALANCETRANSFER_EVENT, transactionReceipt);
        ArrayList<BalanceTransferEventResponse> responses = new ArrayList<BalanceTransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BalanceTransferEventResponse typedResponse = new BalanceTransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._fromBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._toBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse._fromIndex = (Uint256) eventValues.getNonIndexedValues().get(3);
            typedResponse._toIndex = (Uint256) eventValues.getNonIndexedValues().get(4);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BalanceTransferEventResponse> balanceTransferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BalanceTransferEventResponse>() {
            @Override
            public BalanceTransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BALANCETRANSFER_EVENT, log);
                BalanceTransferEventResponse typedResponse = new BalanceTransferEventResponse();
                typedResponse.log = log;
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._fromBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._toBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse._fromIndex = (Uint256) eventValues.getNonIndexedValues().get(3);
                typedResponse._toIndex = (Uint256) eventValues.getNonIndexedValues().get(4);
                return typedResponse;
            }
        });
    }

    public Flowable<BalanceTransferEventResponse> balanceTransferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BALANCETRANSFER_EVENT));
        return balanceTransferEventFlowable(filter);
    }

    public List<BurnOnLiquidationEventResponse> getBurnOnLiquidationEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BURNONLIQUIDATION_EVENT, transactionReceipt);
        ArrayList<BurnOnLiquidationEventResponse> responses = new ArrayList<BurnOnLiquidationEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BurnOnLiquidationEventResponse typedResponse = new BurnOnLiquidationEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._fromBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._fromIndex = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BurnOnLiquidationEventResponse> burnOnLiquidationEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BurnOnLiquidationEventResponse>() {
            @Override
            public BurnOnLiquidationEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BURNONLIQUIDATION_EVENT, log);
                BurnOnLiquidationEventResponse typedResponse = new BurnOnLiquidationEventResponse();
                typedResponse.log = log;
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._fromBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._fromIndex = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<BurnOnLiquidationEventResponse> burnOnLiquidationEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BURNONLIQUIDATION_EVENT));
        return burnOnLiquidationEventFlowable(filter);
    }

    public List<InterestRedirectionAllowanceChangedEventResponse> getInterestRedirectionAllowanceChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(INTERESTREDIRECTIONALLOWANCECHANGED_EVENT, transactionReceipt);
        ArrayList<InterestRedirectionAllowanceChangedEventResponse> responses = new ArrayList<InterestRedirectionAllowanceChangedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InterestRedirectionAllowanceChangedEventResponse typedResponse = new InterestRedirectionAllowanceChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<InterestRedirectionAllowanceChangedEventResponse> interestRedirectionAllowanceChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, InterestRedirectionAllowanceChangedEventResponse>() {
            @Override
            public InterestRedirectionAllowanceChangedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(INTERESTREDIRECTIONALLOWANCECHANGED_EVENT, log);
                InterestRedirectionAllowanceChangedEventResponse typedResponse = new InterestRedirectionAllowanceChangedEventResponse();
                typedResponse.log = log;
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<InterestRedirectionAllowanceChangedEventResponse> interestRedirectionAllowanceChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INTERESTREDIRECTIONALLOWANCECHANGED_EVENT));
        return interestRedirectionAllowanceChangedEventFlowable(filter);
    }

    public List<InterestStreamRedirectedEventResponse> getInterestStreamRedirectedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(INTERESTSTREAMREDIRECTED_EVENT, transactionReceipt);
        ArrayList<InterestStreamRedirectedEventResponse> responses = new ArrayList<InterestStreamRedirectedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            InterestStreamRedirectedEventResponse typedResponse = new InterestStreamRedirectedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._redirectedBalance = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._fromBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._fromIndex = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<InterestStreamRedirectedEventResponse> interestStreamRedirectedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, InterestStreamRedirectedEventResponse>() {
            @Override
            public InterestStreamRedirectedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(INTERESTSTREAMREDIRECTED_EVENT, log);
                InterestStreamRedirectedEventResponse typedResponse = new InterestStreamRedirectedEventResponse();
                typedResponse.log = log;
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._to = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._redirectedBalance = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._fromBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._fromIndex = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<InterestStreamRedirectedEventResponse> interestStreamRedirectedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(INTERESTSTREAMREDIRECTED_EVENT));
        return interestStreamRedirectedEventFlowable(filter);
    }

    public List<MintOnDepositEventResponse> getMintOnDepositEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(MINTONDEPOSIT_EVENT, transactionReceipt);
        ArrayList<MintOnDepositEventResponse> responses = new ArrayList<MintOnDepositEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            MintOnDepositEventResponse typedResponse = new MintOnDepositEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._fromBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._fromIndex = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<MintOnDepositEventResponse> mintOnDepositEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, MintOnDepositEventResponse>() {
            @Override
            public MintOnDepositEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(MINTONDEPOSIT_EVENT, log);
                MintOnDepositEventResponse typedResponse = new MintOnDepositEventResponse();
                typedResponse.log = log;
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._fromBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._fromIndex = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<MintOnDepositEventResponse> mintOnDepositEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(MINTONDEPOSIT_EVENT));
        return mintOnDepositEventFlowable(filter);
    }

    public List<RedeemEventResponse> getRedeemEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REDEEM_EVENT, transactionReceipt);
        ArrayList<RedeemEventResponse> responses = new ArrayList<RedeemEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RedeemEventResponse typedResponse = new RedeemEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._fromBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._fromIndex = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RedeemEventResponse> redeemEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RedeemEventResponse>() {
            @Override
            public RedeemEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REDEEM_EVENT, log);
                RedeemEventResponse typedResponse = new RedeemEventResponse();
                typedResponse.log = log;
                typedResponse._from = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._value = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._fromBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._fromIndex = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<RedeemEventResponse> redeemEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REDEEM_EVENT));
        return redeemEventFlowable(filter);
    }

    public List<RedirectedBalanceUpdatedEventResponse> getRedirectedBalanceUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REDIRECTEDBALANCEUPDATED_EVENT, transactionReceipt);
        ArrayList<RedirectedBalanceUpdatedEventResponse> responses = new ArrayList<RedirectedBalanceUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RedirectedBalanceUpdatedEventResponse typedResponse = new RedirectedBalanceUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._targetAddress = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._targetBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._targetIndex = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._redirectedBalanceAdded = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse._redirectedBalanceRemoved = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RedirectedBalanceUpdatedEventResponse> redirectedBalanceUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RedirectedBalanceUpdatedEventResponse>() {
            @Override
            public RedirectedBalanceUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REDIRECTEDBALANCEUPDATED_EVENT, log);
                RedirectedBalanceUpdatedEventResponse typedResponse = new RedirectedBalanceUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse._targetAddress = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._targetBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._targetIndex = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._redirectedBalanceAdded = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse._redirectedBalanceRemoved = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public Flowable<RedirectedBalanceUpdatedEventResponse> redirectedBalanceUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REDIRECTEDBALANCEUPDATED_EVENT));
        return redirectedBalanceUpdatedEventFlowable(filter);
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
        return web3j.ethLogFlowable(filter).map(new Function<Log, TransferEventResponse>() {
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

    public RemoteCall<Uint256> UINT_MAX_VALUE() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_UINT_MAX_VALUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> allowance(Address owner, Address spender) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(owner, spender), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> approve(Address spender, Uint256 value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(spender, value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint8> decimals() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> decreaseAllowance(Address spender, Uint256 subtractedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DECREASEALLOWANCE, 
                Arrays.<Type>asList(spender, subtractedValue), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> increaseAllowance(Address spender, Uint256 addedValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INCREASEALLOWANCE, 
                Arrays.<Type>asList(spender, addedValue), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Utf8String> name() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Utf8String> symbol() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> transfer(Address recipient, Uint256 amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(recipient, amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferFrom(Address sender, Address recipient, Uint256 amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(sender, recipient, amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> underlyingAssetAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_UNDERLYINGASSETADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> redirectInterestStream(Address _to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REDIRECTINTERESTSTREAM, 
                Arrays.<Type>asList(_to), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> redirectInterestStreamOf(Address _from, Address _to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REDIRECTINTERESTSTREAMOF, 
                Arrays.<Type>asList(_from, _to), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> allowInterestRedirectionTo(Address _to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ALLOWINTERESTREDIRECTIONTO, 
                Arrays.<Type>asList(_to), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> redeem(Uint256 _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REDEEM, 
                Arrays.<Type>asList(_amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> mintOnDeposit(Address _account, Uint256 _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_MINTONDEPOSIT, 
                Arrays.<Type>asList(_account, _amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> burnOnLiquidation(Address _account, Uint256 _value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BURNONLIQUIDATION, 
                Arrays.<Type>asList(_account, _value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOnLiquidation(Address _from, Address _to, Uint256 _value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERONLIQUIDATION, 
                Arrays.<Type>asList(_from, _to, _value), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> balanceOf(Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(_user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> principalBalanceOf(Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PRINCIPALBALANCEOF, 
                Arrays.<Type>asList(_user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> isTransferAllowed(Address _user, Uint256 _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISTRANSFERALLOWED, 
                Arrays.<Type>asList(_user, _amount), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getUserIndex(Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERINDEX, 
                Arrays.<Type>asList(_user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> getInterestRedirectionAddress(Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETINTERESTREDIRECTIONADDRESS, 
                Arrays.<Type>asList(_user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getRedirectedBalance(Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETREDIRECTEDBALANCE, 
                Arrays.<Type>asList(_user), 
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

    public static class ApprovalEventResponse {
        public Log log;

        public Address owner;

        public Address spender;

        public Uint256 value;
    }

    public static class BalanceTransferEventResponse {
        public Log log;

        public Address _from;

        public Address _to;

        public Uint256 _value;

        public Uint256 _fromBalanceIncrease;

        public Uint256 _toBalanceIncrease;

        public Uint256 _fromIndex;

        public Uint256 _toIndex;
    }

    public static class BurnOnLiquidationEventResponse {
        public Log log;

        public Address _from;

        public Uint256 _value;

        public Uint256 _fromBalanceIncrease;

        public Uint256 _fromIndex;
    }

    public static class InterestRedirectionAllowanceChangedEventResponse {
        public Log log;

        public Address _from;

        public Address _to;
    }

    public static class InterestStreamRedirectedEventResponse {
        public Log log;

        public Address _from;

        public Address _to;

        public Uint256 _redirectedBalance;

        public Uint256 _fromBalanceIncrease;

        public Uint256 _fromIndex;
    }

    public static class MintOnDepositEventResponse {
        public Log log;

        public Address _from;

        public Uint256 _value;

        public Uint256 _fromBalanceIncrease;

        public Uint256 _fromIndex;
    }

    public static class RedeemEventResponse {
        public Log log;

        public Address _from;

        public Uint256 _value;

        public Uint256 _fromBalanceIncrease;

        public Uint256 _fromIndex;
    }

    public static class RedirectedBalanceUpdatedEventResponse {
        public Log log;

        public Address _targetAddress;

        public Uint256 _targetBalanceIncrease;

        public Uint256 _targetIndex;

        public Uint256 _redirectedBalanceAdded;

        public Uint256 _redirectedBalanceRemoved;
    }

    public static class TransferEventResponse {
        public Log log;

        public Address from;

        public Address to;

        public Uint256 value;
    }
}
