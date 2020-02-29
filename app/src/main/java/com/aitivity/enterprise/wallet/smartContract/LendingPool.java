package com.aitivity.enterprise.wallet.smartContract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint16;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint40;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple10;
import org.web3j.tuples.generated.Tuple13;
import org.web3j.tuples.generated.Tuple8;
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
public class LendingPool extends Contract {
    private static final String BINARY = "Bin file was not provided";

    public static final String FUNC_LENDINGPOOL_REVISION = "LENDINGPOOL_REVISION";

    public static final String FUNC_UINT_MAX_VALUE = "UINT_MAX_VALUE";

    public static final String FUNC_ADDRESSESPROVIDER = "addressesProvider";

    public static final String FUNC_CORE = "core";

    public static final String FUNC_DATAPROVIDER = "dataProvider";

    public static final String FUNC_PARAMETERSPROVIDER = "parametersProvider";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_REDEEMUNDERLYING = "redeemUnderlying";

    public static final String FUNC_BORROW = "borrow";

    public static final String FUNC_REPAY = "repay";

    public static final String FUNC_SWAPBORROWRATEMODE = "swapBorrowRateMode";

    public static final String FUNC_REBALANCESTABLEBORROWRATE = "rebalanceStableBorrowRate";

    public static final String FUNC_SETUSERUSERESERVEASCOLLATERAL = "setUserUseReserveAsCollateral";

    public static final String FUNC_LIQUIDATIONCALL = "liquidationCall";

    public static final String FUNC_FLASHLOAN = "flashLoan";

    public static final String FUNC_GETRESERVECONFIGURATIONDATA = "getReserveConfigurationData";

    public static final String FUNC_GETRESERVEDATA = "getReserveData";

    public static final String FUNC_GETUSERACCOUNTDATA = "getUserAccountData";

    public static final String FUNC_GETUSERRESERVEDATA = "getUserReserveData";

    public static final String FUNC_GETRESERVES = "getReserves";

    public static final Event BORROW_EVENT = new Event("Borrow", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint16>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event DEPOSIT_EVENT = new Event("Deposit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint16>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event FLASHLOAN_EVENT = new Event("FlashLoan", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LIQUIDATIONCALL_EVENT = new Event("LiquidationCall", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event ORIGINATIONFEELIQUIDATED_EVENT = new Event("OriginationFeeLiquidated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REBALANCESTABLEBORROWRATE_EVENT = new Event("RebalanceStableBorrowRate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REDEEMUNDERLYING_EVENT = new Event("RedeemUnderlying", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event REPAY_EVENT = new Event("Repay", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event RESERVEUSEDASCOLLATERALDISABLED_EVENT = new Event("ReserveUsedAsCollateralDisabled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event RESERVEUSEDASCOLLATERALENABLED_EVENT = new Event("ReserveUsedAsCollateralEnabled", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event SWAP_EVENT = new Event("Swap", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected LendingPool(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LendingPool(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected LendingPool(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected LendingPool(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<BorrowEventResponse> getBorrowEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(BORROW_EVENT, transactionReceipt);
        ArrayList<BorrowEventResponse> responses = new ArrayList<BorrowEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            BorrowEventResponse typedResponse = new BorrowEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._referral = (Uint16) eventValues.getIndexedValues().get(2);
            typedResponse._amount = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._borrowRateMode = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._borrowRate = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse._originationFee = (Uint256) eventValues.getNonIndexedValues().get(3);
            typedResponse._borrowBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(4);
            typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(5);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<BorrowEventResponse> borrowEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, BorrowEventResponse>() {
            @Override
            public BorrowEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(BORROW_EVENT, log);
                BorrowEventResponse typedResponse = new BorrowEventResponse();
                typedResponse.log = log;
                typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._referral = (Uint16) eventValues.getIndexedValues().get(2);
                typedResponse._amount = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._borrowRateMode = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._borrowRate = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse._originationFee = (Uint256) eventValues.getNonIndexedValues().get(3);
                typedResponse._borrowBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(4);
                typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(5);
                return typedResponse;
            }
        });
    }

    public Flowable<BorrowEventResponse> borrowEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(BORROW_EVENT));
        return borrowEventFlowable(filter);
    }

    public List<DepositEventResponse> getDepositEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(DEPOSIT_EVENT, transactionReceipt);
        ArrayList<DepositEventResponse> responses = new ArrayList<DepositEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            DepositEventResponse typedResponse = new DepositEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._referral = (Uint16) eventValues.getIndexedValues().get(2);
            typedResponse._amount = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DepositEventResponse> depositEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DepositEventResponse>() {
            @Override
            public DepositEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(DEPOSIT_EVENT, log);
                DepositEventResponse typedResponse = new DepositEventResponse();
                typedResponse.log = log;
                typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._referral = (Uint16) eventValues.getIndexedValues().get(2);
                typedResponse._amount = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<DepositEventResponse> depositEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DEPOSIT_EVENT));
        return depositEventFlowable(filter);
    }

    public List<FlashLoanEventResponse> getFlashLoanEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FLASHLOAN_EVENT, transactionReceipt);
        ArrayList<FlashLoanEventResponse> responses = new ArrayList<FlashLoanEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FlashLoanEventResponse typedResponse = new FlashLoanEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._target = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._reserve = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._amount = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._totalFee = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._protocolFee = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FlashLoanEventResponse> flashLoanEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, FlashLoanEventResponse>() {
            @Override
            public FlashLoanEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FLASHLOAN_EVENT, log);
                FlashLoanEventResponse typedResponse = new FlashLoanEventResponse();
                typedResponse.log = log;
                typedResponse._target = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._reserve = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._amount = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._totalFee = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._protocolFee = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public Flowable<FlashLoanEventResponse> flashLoanEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FLASHLOAN_EVENT));
        return flashLoanEventFlowable(filter);
    }

    public List<LiquidationCallEventResponse> getLiquidationCallEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LIQUIDATIONCALL_EVENT, transactionReceipt);
        ArrayList<LiquidationCallEventResponse> responses = new ArrayList<LiquidationCallEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LiquidationCallEventResponse typedResponse = new LiquidationCallEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._collateral = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._reserve = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._user = (Address) eventValues.getIndexedValues().get(2);
            typedResponse._purchaseAmount = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._liquidatedCollateralAmount = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._accruedBorrowInterest = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse._liquidator = (Address) eventValues.getNonIndexedValues().get(3);
            typedResponse._receiveAToken = (Bool) eventValues.getNonIndexedValues().get(4);
            typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(5);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LiquidationCallEventResponse> liquidationCallEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LiquidationCallEventResponse>() {
            @Override
            public LiquidationCallEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LIQUIDATIONCALL_EVENT, log);
                LiquidationCallEventResponse typedResponse = new LiquidationCallEventResponse();
                typedResponse.log = log;
                typedResponse._collateral = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._reserve = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._user = (Address) eventValues.getIndexedValues().get(2);
                typedResponse._purchaseAmount = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._liquidatedCollateralAmount = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._accruedBorrowInterest = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse._liquidator = (Address) eventValues.getNonIndexedValues().get(3);
                typedResponse._receiveAToken = (Bool) eventValues.getNonIndexedValues().get(4);
                typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(5);
                return typedResponse;
            }
        });
    }

    public Flowable<LiquidationCallEventResponse> liquidationCallEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LIQUIDATIONCALL_EVENT));
        return liquidationCallEventFlowable(filter);
    }

    public List<OriginationFeeLiquidatedEventResponse> getOriginationFeeLiquidatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ORIGINATIONFEELIQUIDATED_EVENT, transactionReceipt);
        ArrayList<OriginationFeeLiquidatedEventResponse> responses = new ArrayList<OriginationFeeLiquidatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OriginationFeeLiquidatedEventResponse typedResponse = new OriginationFeeLiquidatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._collateral = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._reserve = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._user = (Address) eventValues.getIndexedValues().get(2);
            typedResponse._feeLiquidated = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._liquidatedCollateralForFee = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OriginationFeeLiquidatedEventResponse> originationFeeLiquidatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OriginationFeeLiquidatedEventResponse>() {
            @Override
            public OriginationFeeLiquidatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ORIGINATIONFEELIQUIDATED_EVENT, log);
                OriginationFeeLiquidatedEventResponse typedResponse = new OriginationFeeLiquidatedEventResponse();
                typedResponse.log = log;
                typedResponse._collateral = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._reserve = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._user = (Address) eventValues.getIndexedValues().get(2);
                typedResponse._feeLiquidated = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._liquidatedCollateralForFee = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<OriginationFeeLiquidatedEventResponse> originationFeeLiquidatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ORIGINATIONFEELIQUIDATED_EVENT));
        return originationFeeLiquidatedEventFlowable(filter);
    }

    public List<RebalanceStableBorrowRateEventResponse> getRebalanceStableBorrowRateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REBALANCESTABLEBORROWRATE_EVENT, transactionReceipt);
        ArrayList<RebalanceStableBorrowRateEventResponse> responses = new ArrayList<RebalanceStableBorrowRateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RebalanceStableBorrowRateEventResponse typedResponse = new RebalanceStableBorrowRateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._newStableRate = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._borrowBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RebalanceStableBorrowRateEventResponse> rebalanceStableBorrowRateEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RebalanceStableBorrowRateEventResponse>() {
            @Override
            public RebalanceStableBorrowRateEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REBALANCESTABLEBORROWRATE_EVENT, log);
                RebalanceStableBorrowRateEventResponse typedResponse = new RebalanceStableBorrowRateEventResponse();
                typedResponse.log = log;
                typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._newStableRate = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._borrowBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<RebalanceStableBorrowRateEventResponse> rebalanceStableBorrowRateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REBALANCESTABLEBORROWRATE_EVENT));
        return rebalanceStableBorrowRateEventFlowable(filter);
    }

    public List<RedeemUnderlyingEventResponse> getRedeemUnderlyingEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REDEEMUNDERLYING_EVENT, transactionReceipt);
        ArrayList<RedeemUnderlyingEventResponse> responses = new ArrayList<RedeemUnderlyingEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RedeemUnderlyingEventResponse typedResponse = new RedeemUnderlyingEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._amount = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RedeemUnderlyingEventResponse> redeemUnderlyingEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RedeemUnderlyingEventResponse>() {
            @Override
            public RedeemUnderlyingEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REDEEMUNDERLYING_EVENT, log);
                RedeemUnderlyingEventResponse typedResponse = new RedeemUnderlyingEventResponse();
                typedResponse.log = log;
                typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._amount = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<RedeemUnderlyingEventResponse> redeemUnderlyingEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REDEEMUNDERLYING_EVENT));
        return redeemUnderlyingEventFlowable(filter);
    }

    public List<RepayEventResponse> getRepayEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(REPAY_EVENT, transactionReceipt);
        ArrayList<RepayEventResponse> responses = new ArrayList<RepayEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            RepayEventResponse typedResponse = new RepayEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._repayer = (Address) eventValues.getIndexedValues().get(2);
            typedResponse._amountMinusFees = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._fees = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._borrowBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RepayEventResponse> repayEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RepayEventResponse>() {
            @Override
            public RepayEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(REPAY_EVENT, log);
                RepayEventResponse typedResponse = new RepayEventResponse();
                typedResponse.log = log;
                typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._repayer = (Address) eventValues.getIndexedValues().get(2);
                typedResponse._amountMinusFees = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._fees = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._borrowBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public Flowable<RepayEventResponse> repayEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(REPAY_EVENT));
        return repayEventFlowable(filter);
    }

    public List<ReserveUsedAsCollateralDisabledEventResponse> getReserveUsedAsCollateralDisabledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RESERVEUSEDASCOLLATERALDISABLED_EVENT, transactionReceipt);
        ArrayList<ReserveUsedAsCollateralDisabledEventResponse> responses = new ArrayList<ReserveUsedAsCollateralDisabledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReserveUsedAsCollateralDisabledEventResponse typedResponse = new ReserveUsedAsCollateralDisabledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ReserveUsedAsCollateralDisabledEventResponse> reserveUsedAsCollateralDisabledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ReserveUsedAsCollateralDisabledEventResponse>() {
            @Override
            public ReserveUsedAsCollateralDisabledEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RESERVEUSEDASCOLLATERALDISABLED_EVENT, log);
                ReserveUsedAsCollateralDisabledEventResponse typedResponse = new ReserveUsedAsCollateralDisabledEventResponse();
                typedResponse.log = log;
                typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<ReserveUsedAsCollateralDisabledEventResponse> reserveUsedAsCollateralDisabledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RESERVEUSEDASCOLLATERALDISABLED_EVENT));
        return reserveUsedAsCollateralDisabledEventFlowable(filter);
    }

    public List<ReserveUsedAsCollateralEnabledEventResponse> getReserveUsedAsCollateralEnabledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RESERVEUSEDASCOLLATERALENABLED_EVENT, transactionReceipt);
        ArrayList<ReserveUsedAsCollateralEnabledEventResponse> responses = new ArrayList<ReserveUsedAsCollateralEnabledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReserveUsedAsCollateralEnabledEventResponse typedResponse = new ReserveUsedAsCollateralEnabledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ReserveUsedAsCollateralEnabledEventResponse> reserveUsedAsCollateralEnabledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ReserveUsedAsCollateralEnabledEventResponse>() {
            @Override
            public ReserveUsedAsCollateralEnabledEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RESERVEUSEDASCOLLATERALENABLED_EVENT, log);
                ReserveUsedAsCollateralEnabledEventResponse typedResponse = new ReserveUsedAsCollateralEnabledEventResponse();
                typedResponse.log = log;
                typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<ReserveUsedAsCollateralEnabledEventResponse> reserveUsedAsCollateralEnabledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RESERVEUSEDASCOLLATERALENABLED_EVENT));
        return reserveUsedAsCollateralEnabledEventFlowable(filter);
    }

    public List<SwapEventResponse> getSwapEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(SWAP_EVENT, transactionReceipt);
        ArrayList<SwapEventResponse> responses = new ArrayList<SwapEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            SwapEventResponse typedResponse = new SwapEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
            typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
            typedResponse._newRateMode = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse._newRate = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse._borrowBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SwapEventResponse> swapEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SwapEventResponse>() {
            @Override
            public SwapEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(SWAP_EVENT, log);
                SwapEventResponse typedResponse = new SwapEventResponse();
                typedResponse.log = log;
                typedResponse._reserve = (Address) eventValues.getIndexedValues().get(0);
                typedResponse._user = (Address) eventValues.getIndexedValues().get(1);
                typedResponse._newRateMode = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse._newRate = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse._borrowBalanceIncrease = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse._timestamp = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public Flowable<SwapEventResponse> swapEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SWAP_EVENT));
        return swapEventFlowable(filter);
    }

    public RemoteCall<Uint256> LENDINGPOOL_REVISION() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LENDINGPOOL_REVISION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> UINT_MAX_VALUE() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_UINT_MAX_VALUE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> addressesProvider() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ADDRESSESPROVIDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> core() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CORE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> dataProvider() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_DATAPROVIDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> parametersProvider() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_PARAMETERSPROVIDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> initialize(Address _addressesProvider) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(_addressesProvider), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> deposit(Address _reserve, Uint256 _amount, Uint16 _referralCode, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(_reserve, _amount, _referralCode), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> redeemUnderlying(Address _reserve, Address _user, Uint256 _amount, Uint256 _aTokenBalanceAfterRedeem) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REDEEMUNDERLYING, 
                Arrays.<Type>asList(_reserve, _user, _amount, _aTokenBalanceAfterRedeem), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> borrow(Address _reserve, Uint256 _amount, Uint256 _interestRateMode, Uint16 _referralCode) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_BORROW, 
                Arrays.<Type>asList(_reserve, _amount, _interestRateMode, _referralCode), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> repay(Address _reserve, Uint256 _amount, Address _onBehalfOf, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REPAY, 
                Arrays.<Type>asList(_reserve, _amount, _onBehalfOf), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> swapBorrowRateMode(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SWAPBORROWRATEMODE, 
                Arrays.<Type>asList(_reserve), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> rebalanceStableBorrowRate(Address _reserve, Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REBALANCESTABLEBORROWRATE, 
                Arrays.<Type>asList(_reserve, _user), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setUserUseReserveAsCollateral(Address _reserve, Bool _useAsCollateral) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETUSERUSERESERVEASCOLLATERAL, 
                Arrays.<Type>asList(_reserve, _useAsCollateral), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> liquidationCall(Address _collateral, Address _reserve, Address _user, Uint256 _purchaseAmount, Bool _receiveAToken, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LIQUIDATIONCALL, 
                Arrays.<Type>asList(_collateral, _reserve, _user, _purchaseAmount, _receiveAToken), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> flashLoan(Address _receiver, Address _reserve, Uint256 _amount, DynamicBytes _params) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FLASHLOAN, 
                Arrays.<Type>asList(_receiver, _reserve, _amount, _params), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple8<Uint256, Uint256, Uint256, Address, Bool, Bool, Bool, Bool>> getReserveConfigurationData(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVECONFIGURATIONDATA, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple8<Uint256, Uint256, Uint256, Address, Bool, Bool, Bool, Bool>>(
                new Callable<Tuple8<Uint256, Uint256, Uint256, Address, Bool, Bool, Bool, Bool>>() {
                    @Override
                    public Tuple8<Uint256, Uint256, Uint256, Address, Bool, Bool, Bool, Bool> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<Uint256, Uint256, Uint256, Address, Bool, Bool, Bool, Bool>(
                                (Uint256) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2), 
                                (Address) results.get(3), 
                                (Bool) results.get(4), 
                                (Bool) results.get(5), 
                                (Bool) results.get(6), 
                                (Bool) results.get(7));
                    }
                });
    }

    public RemoteCall<Tuple13<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Address, Uint40>> getReserveData(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVEDATA, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint40>() {}));
        return new RemoteCall<Tuple13<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Address, Uint40>>(
                new Callable<Tuple13<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Address, Uint40>>() {
                    @Override
                    public Tuple13<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Address, Uint40> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple13<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Address, Uint40>(
                                (Uint256) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2), 
                                (Uint256) results.get(3), 
                                (Uint256) results.get(4), 
                                (Uint256) results.get(5), 
                                (Uint256) results.get(6), 
                                (Uint256) results.get(7), 
                                (Uint256) results.get(8), 
                                (Uint256) results.get(9), 
                                (Uint256) results.get(10), 
                                (Address) results.get(11), 
                                (Uint40) results.get(12));
                    }
                });
    }

    public RemoteCall<Tuple8<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256>> getUserAccountData(Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERACCOUNTDATA, 
                Arrays.<Type>asList(_user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple8<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256>>(
                new Callable<Tuple8<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256>>() {
                    @Override
                    public Tuple8<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple8<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256>(
                                (Uint256) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2), 
                                (Uint256) results.get(3), 
                                (Uint256) results.get(4), 
                                (Uint256) results.get(5), 
                                (Uint256) results.get(6), 
                                (Uint256) results.get(7));
                    }
                });
    }

    public RemoteCall<Tuple10<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Bool>> getUserReserveData(Address _reserve, Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERRESERVEDATA, 
                Arrays.<Type>asList(_reserve, _user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple10<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Bool>>(
                new Callable<Tuple10<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Bool>>() {
                    @Override
                    public Tuple10<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Bool> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple10<Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Uint256, Bool>(
                                (Uint256) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2), 
                                (Uint256) results.get(3), 
                                (Uint256) results.get(4), 
                                (Uint256) results.get(5), 
                                (Uint256) results.get(6), 
                                (Uint256) results.get(7), 
                                (Uint256) results.get(8), 
                                (Bool) results.get(9));
                    }
                });
    }

    public RemoteCall<DynamicArray<Address>> getReserves() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    @Deprecated
    public static LendingPool load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new LendingPool(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static LendingPool load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LendingPool(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static LendingPool load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new LendingPool(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static LendingPool load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new LendingPool(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class BorrowEventResponse {
        public Log log;

        public Address _reserve;

        public Address _user;

        public Uint16 _referral;

        public Uint256 _amount;

        public Uint256 _borrowRateMode;

        public Uint256 _borrowRate;

        public Uint256 _originationFee;

        public Uint256 _borrowBalanceIncrease;

        public Uint256 _timestamp;
    }

    public static class DepositEventResponse {
        public Log log;

        public Address _reserve;

        public Address _user;

        public Uint16 _referral;

        public Uint256 _amount;

        public Uint256 _timestamp;
    }

    public static class FlashLoanEventResponse {
        public Log log;

        public Address _target;

        public Address _reserve;

        public Uint256 _amount;

        public Uint256 _totalFee;

        public Uint256 _protocolFee;

        public Uint256 _timestamp;
    }

    public static class LiquidationCallEventResponse {
        public Log log;

        public Address _collateral;

        public Address _reserve;

        public Address _user;

        public Uint256 _purchaseAmount;

        public Uint256 _liquidatedCollateralAmount;

        public Uint256 _accruedBorrowInterest;

        public Address _liquidator;

        public Bool _receiveAToken;

        public Uint256 _timestamp;
    }

    public static class OriginationFeeLiquidatedEventResponse {
        public Log log;

        public Address _collateral;

        public Address _reserve;

        public Address _user;

        public Uint256 _feeLiquidated;

        public Uint256 _liquidatedCollateralForFee;

        public Uint256 _timestamp;
    }

    public static class RebalanceStableBorrowRateEventResponse {
        public Log log;

        public Address _reserve;

        public Address _user;

        public Uint256 _newStableRate;

        public Uint256 _borrowBalanceIncrease;

        public Uint256 _timestamp;
    }

    public static class RedeemUnderlyingEventResponse {
        public Log log;

        public Address _reserve;

        public Address _user;

        public Uint256 _amount;

        public Uint256 _timestamp;
    }

    public static class RepayEventResponse {
        public Log log;

        public Address _reserve;

        public Address _user;

        public Address _repayer;

        public Uint256 _amountMinusFees;

        public Uint256 _fees;

        public Uint256 _borrowBalanceIncrease;

        public Uint256 _timestamp;
    }

    public static class ReserveUsedAsCollateralDisabledEventResponse {
        public Log log;

        public Address _reserve;

        public Address _user;
    }

    public static class ReserveUsedAsCollateralEnabledEventResponse {
        public Log log;

        public Address _reserve;

        public Address _user;
    }

    public static class SwapEventResponse {
        public Log log;

        public Address _reserve;

        public Address _user;

        public Uint256 _newRateMode;

        public Uint256 _newRate;

        public Uint256 _borrowBalanceIncrease;

        public Uint256 _timestamp;
    }
}
