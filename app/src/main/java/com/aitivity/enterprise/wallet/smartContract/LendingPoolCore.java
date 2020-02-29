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
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint40;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple4;
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
public class LendingPoolCore extends Contract {
    private static final String BINARY = "Bin file was not provided";

    public static final String FUNC_CORE_REVISION = "CORE_REVISION";

    public static final String FUNC_ADDRESSESPROVIDER = "addressesProvider";

    public static final String FUNC_LENDINGPOOLADDRESS = "lendingPoolAddress";

    public static final String FUNC_RESERVESLIST = "reservesList";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_UPDATESTATEONDEPOSIT = "updateStateOnDeposit";

    public static final String FUNC_UPDATESTATEONREDEEM = "updateStateOnRedeem";

    public static final String FUNC_UPDATESTATEONFLASHLOAN = "updateStateOnFlashLoan";

    public static final String FUNC_UPDATESTATEONBORROW = "updateStateOnBorrow";

    public static final String FUNC_UPDATESTATEONREPAY = "updateStateOnRepay";

    public static final String FUNC_UPDATESTATEONSWAPRATE = "updateStateOnSwapRate";

    public static final String FUNC_UPDATESTATEONLIQUIDATION = "updateStateOnLiquidation";

    public static final String FUNC_UPDATESTATEONREBALANCE = "updateStateOnRebalance";

    public static final String FUNC_SETUSERUSERESERVEASCOLLATERAL = "setUserUseReserveAsCollateral";

    public static final String FUNC_TRANSFERTOUSER = "transferToUser";

    public static final String FUNC_TRANSFERTOFEECOLLECTIONADDRESS = "transferToFeeCollectionAddress";

    public static final String FUNC_LIQUIDATEFEE = "liquidateFee";

    public static final String FUNC_TRANSFERTORESERVE = "transferToReserve";

    public static final String FUNC_GETUSERBASICRESERVEDATA = "getUserBasicReserveData";

    public static final String FUNC_ISUSERALLOWEDTOBORROWATSTABLE = "isUserAllowedToBorrowAtStable";

    public static final String FUNC_GETUSERUNDERLYINGASSETBALANCE = "getUserUnderlyingAssetBalance";

    public static final String FUNC_GETRESERVEINTERESTRATESTRATEGYADDRESS = "getReserveInterestRateStrategyAddress";

    public static final String FUNC_GETRESERVEATOKENADDRESS = "getReserveATokenAddress";

    public static final String FUNC_GETRESERVEAVAILABLELIQUIDITY = "getReserveAvailableLiquidity";

    public static final String FUNC_GETRESERVETOTALLIQUIDITY = "getReserveTotalLiquidity";

    public static final String FUNC_GETRESERVENORMALIZEDINCOME = "getReserveNormalizedIncome";

    public static final String FUNC_GETRESERVETOTALBORROWS = "getReserveTotalBorrows";

    public static final String FUNC_GETRESERVETOTALBORROWSSTABLE = "getReserveTotalBorrowsStable";

    public static final String FUNC_GETRESERVETOTALBORROWSVARIABLE = "getReserveTotalBorrowsVariable";

    public static final String FUNC_GETRESERVELIQUIDATIONTHRESHOLD = "getReserveLiquidationThreshold";

    public static final String FUNC_GETRESERVELIQUIDATIONBONUS = "getReserveLiquidationBonus";

    public static final String FUNC_GETRESERVECURRENTVARIABLEBORROWRATE = "getReserveCurrentVariableBorrowRate";

    public static final String FUNC_GETRESERVECURRENTSTABLEBORROWRATE = "getReserveCurrentStableBorrowRate";

    public static final String FUNC_GETRESERVECURRENTAVERAGESTABLEBORROWRATE = "getReserveCurrentAverageStableBorrowRate";

    public static final String FUNC_GETRESERVECURRENTLIQUIDITYRATE = "getReserveCurrentLiquidityRate";

    public static final String FUNC_GETRESERVELIQUIDITYCUMULATIVEINDEX = "getReserveLiquidityCumulativeIndex";

    public static final String FUNC_GETRESERVEVARIABLEBORROWSCUMULATIVEINDEX = "getReserveVariableBorrowsCumulativeIndex";

    public static final String FUNC_GETRESERVECONFIGURATION = "getReserveConfiguration";

    public static final String FUNC_GETRESERVEDECIMALS = "getReserveDecimals";

    public static final String FUNC_ISRESERVEBORROWINGENABLED = "isReserveBorrowingEnabled";

    public static final String FUNC_ISRESERVEUSAGEASCOLLATERALENABLED = "isReserveUsageAsCollateralEnabled";

    public static final String FUNC_GETRESERVEISSTABLEBORROWRATEENABLED = "getReserveIsStableBorrowRateEnabled";

    public static final String FUNC_GETRESERVEISACTIVE = "getReserveIsActive";

    public static final String FUNC_GETRESERVEISFREEZED = "getReserveIsFreezed";

    public static final String FUNC_GETRESERVELASTUPDATE = "getReserveLastUpdate";

    public static final String FUNC_GETRESERVEUTILIZATIONRATE = "getReserveUtilizationRate";

    public static final String FUNC_GETRESERVES = "getReserves";

    public static final String FUNC_ISUSERUSERESERVEASCOLLATERALENABLED = "isUserUseReserveAsCollateralEnabled";

    public static final String FUNC_GETUSERORIGINATIONFEE = "getUserOriginationFee";

    public static final String FUNC_GETUSERCURRENTBORROWRATEMODE = "getUserCurrentBorrowRateMode";

    public static final String FUNC_GETUSERCURRENTSTABLEBORROWRATE = "getUserCurrentStableBorrowRate";

    public static final String FUNC_GETUSERBORROWBALANCES = "getUserBorrowBalances";

    public static final String FUNC_GETUSERVARIABLEBORROWCUMULATIVEINDEX = "getUserVariableBorrowCumulativeIndex";

    public static final String FUNC_GETUSERLASTUPDATE = "getUserLastUpdate";

    public static final String FUNC_REFRESHCONFIGURATION = "refreshConfiguration";

    public static final String FUNC_INITRESERVE = "initReserve";

    public static final String FUNC_SETRESERVEINTERESTRATESTRATEGYADDRESS = "setReserveInterestRateStrategyAddress";

    public static final String FUNC_ENABLEBORROWINGONRESERVE = "enableBorrowingOnReserve";

    public static final String FUNC_DISABLEBORROWINGONRESERVE = "disableBorrowingOnReserve";

    public static final String FUNC_ENABLERESERVEASCOLLATERAL = "enableReserveAsCollateral";

    public static final String FUNC_DISABLERESERVEASCOLLATERAL = "disableReserveAsCollateral";

    public static final String FUNC_ENABLERESERVESTABLEBORROWRATE = "enableReserveStableBorrowRate";

    public static final String FUNC_DISABLERESERVESTABLEBORROWRATE = "disableReserveStableBorrowRate";

    public static final String FUNC_ACTIVATERESERVE = "activateReserve";

    public static final String FUNC_DEACTIVATERESERVE = "deactivateReserve";

    public static final String FUNC_FREEZERESERVE = "freezeReserve";

    public static final String FUNC_UNFREEZERESERVE = "unfreezeReserve";

    public static final String FUNC_SETRESERVEBASELTVASCOLLATERAL = "setReserveBaseLTVasCollateral";

    public static final String FUNC_SETRESERVELIQUIDATIONTHRESHOLD = "setReserveLiquidationThreshold";

    public static final String FUNC_SETRESERVELIQUIDATIONBONUS = "setReserveLiquidationBonus";

    public static final String FUNC_SETRESERVEDECIMALS = "setReserveDecimals";

    public static final Event RESERVEUPDATED_EVENT = new Event("ReserveUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected LendingPoolCore(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LendingPoolCore(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected LendingPoolCore(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected LendingPoolCore(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<ReserveUpdatedEventResponse> getReserveUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(RESERVEUPDATED_EVENT, transactionReceipt);
        ArrayList<ReserveUpdatedEventResponse> responses = new ArrayList<ReserveUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ReserveUpdatedEventResponse typedResponse = new ReserveUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.reserve = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.liquidityRate = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.stableBorrowRate = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.variableBorrowRate = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse.liquidityIndex = (Uint256) eventValues.getNonIndexedValues().get(3);
            typedResponse.variableBorrowIndex = (Uint256) eventValues.getNonIndexedValues().get(4);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ReserveUpdatedEventResponse> reserveUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ReserveUpdatedEventResponse>() {
            @Override
            public ReserveUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(RESERVEUPDATED_EVENT, log);
                ReserveUpdatedEventResponse typedResponse = new ReserveUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.reserve = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.liquidityRate = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.stableBorrowRate = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse.variableBorrowRate = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse.liquidityIndex = (Uint256) eventValues.getNonIndexedValues().get(3);
                typedResponse.variableBorrowIndex = (Uint256) eventValues.getNonIndexedValues().get(4);
                return typedResponse;
            }
        });
    }

    public Flowable<ReserveUpdatedEventResponse> reserveUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RESERVEUPDATED_EVENT));
        return reserveUpdatedEventFlowable(filter);
    }

    public RemoteCall<Uint256> CORE_REVISION() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CORE_REVISION, 
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

    public RemoteCall<Address> lendingPoolAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_LENDINGPOOLADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> reservesList(Uint256 param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_RESERVESLIST, 
                Arrays.<Type>asList(param0), 
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

    public RemoteCall<TransactionReceipt> updateStateOnDeposit(Address _reserve, Address _user, Uint256 _amount, Bool _isFirstDeposit) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATESTATEONDEPOSIT, 
                Arrays.<Type>asList(_reserve, _user, _amount, _isFirstDeposit), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateStateOnRedeem(Address _reserve, Address _user, Uint256 _amountRedeemed, Bool _userRedeemedEverything) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATESTATEONREDEEM, 
                Arrays.<Type>asList(_reserve, _user, _amountRedeemed, _userRedeemedEverything), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateStateOnFlashLoan(Address _reserve, Uint256 _availableLiquidityBefore, Uint256 _income, Uint256 _protocolFee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATESTATEONFLASHLOAN, 
                Arrays.<Type>asList(_reserve, _availableLiquidityBefore, _income, _protocolFee), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateStateOnBorrow(Address _reserve, Address _user, Uint256 _amountBorrowed, Uint256 _borrowFee, Uint8 _rateMode) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATESTATEONBORROW, 
                Arrays.<Type>asList(_reserve, _user, _amountBorrowed, _borrowFee, _rateMode), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateStateOnRepay(Address _reserve, Address _user, Uint256 _paybackAmountMinusFees, Uint256 _originationFeeRepaid, Uint256 _balanceIncrease, Bool _repaidWholeLoan) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATESTATEONREPAY, 
                Arrays.<Type>asList(_reserve, _user, _paybackAmountMinusFees, _originationFeeRepaid, _balanceIncrease, _repaidWholeLoan), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateStateOnSwapRate(Address _reserve, Address _user, Uint256 _principalBorrowBalance, Uint256 _compoundedBorrowBalance, Uint256 _balanceIncrease, Uint8 _currentRateMode) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATESTATEONSWAPRATE, 
                Arrays.<Type>asList(_reserve, _user, _principalBorrowBalance, _compoundedBorrowBalance, _balanceIncrease, _currentRateMode), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateStateOnLiquidation(Address _principalReserve, Address _collateralReserve, Address _user, Uint256 _amountToLiquidate, Uint256 _collateralToLiquidate, Uint256 _feeLiquidated, Uint256 _liquidatedCollateralForFee, Uint256 _balanceIncrease, Bool _liquidatorReceivesAToken) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATESTATEONLIQUIDATION, 
                Arrays.<Type>asList(_principalReserve, _collateralReserve, _user, _amountToLiquidate, _collateralToLiquidate, _feeLiquidated, _liquidatedCollateralForFee, _balanceIncrease, _liquidatorReceivesAToken), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> updateStateOnRebalance(Address _reserve, Address _user, Uint256 _balanceIncrease) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UPDATESTATEONREBALANCE, 
                Arrays.<Type>asList(_reserve, _user, _balanceIncrease), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setUserUseReserveAsCollateral(Address _reserve, Address _user, Bool _useAsCollateral) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETUSERUSERESERVEASCOLLATERAL, 
                Arrays.<Type>asList(_reserve, _user, _useAsCollateral), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferToUser(Address _reserve, Address _user, Uint256 _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERTOUSER, 
                Arrays.<Type>asList(_reserve, _user, _amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferToFeeCollectionAddress(Address _token, Address _user, Uint256 _amount, Address _destination, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERTOFEECOLLECTIONADDRESS, 
                Arrays.<Type>asList(_token, _user, _amount, _destination), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> liquidateFee(Address _token, Uint256 _amount, Address _destination, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_LIQUIDATEFEE, 
                Arrays.<Type>asList(_token, _amount, _destination), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> transferToReserve(Address _reserve, Address _user, Uint256 _amount, BigInteger weiValue) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFERTORESERVE, 
                Arrays.<Type>asList(_reserve, _user, _amount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Tuple4<Uint256, Uint256, Uint256, Bool>> getUserBasicReserveData(Address _reserve, Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERBASICRESERVEDATA, 
                Arrays.<Type>asList(_reserve, _user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple4<Uint256, Uint256, Uint256, Bool>>(
                new Callable<Tuple4<Uint256, Uint256, Uint256, Bool>>() {
                    @Override
                    public Tuple4<Uint256, Uint256, Uint256, Bool> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<Uint256, Uint256, Uint256, Bool>(
                                (Uint256) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2), 
                                (Bool) results.get(3));
                    }
                });
    }

    public RemoteCall<Bool> isUserAllowedToBorrowAtStable(Address _reserve, Address _user, Uint256 _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISUSERALLOWEDTOBORROWATSTABLE, 
                Arrays.<Type>asList(_reserve, _user, _amount), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getUserUnderlyingAssetBalance(Address _reserve, Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERUNDERLYINGASSETBALANCE, 
                Arrays.<Type>asList(_reserve, _user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> getReserveInterestRateStrategyAddress(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVEINTERESTRATESTRATEGYADDRESS, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> getReserveATokenAddress(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVEATOKENADDRESS, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveAvailableLiquidity(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVEAVAILABLELIQUIDITY, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveTotalLiquidity(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVETOTALLIQUIDITY, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveNormalizedIncome(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVENORMALIZEDINCOME, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveTotalBorrows(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVETOTALBORROWS, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveTotalBorrowsStable(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVETOTALBORROWSSTABLE, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveTotalBorrowsVariable(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVETOTALBORROWSVARIABLE, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveLiquidationThreshold(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVELIQUIDATIONTHRESHOLD, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveLiquidationBonus(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVELIQUIDATIONBONUS, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveCurrentVariableBorrowRate(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVECURRENTVARIABLEBORROWRATE, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveCurrentStableBorrowRate(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVECURRENTSTABLEBORROWRATE, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveCurrentAverageStableBorrowRate(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVECURRENTAVERAGESTABLEBORROWRATE, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveCurrentLiquidityRate(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVECURRENTLIQUIDITYRATE, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveLiquidityCumulativeIndex(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVELIQUIDITYCUMULATIVEINDEX, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveVariableBorrowsCumulativeIndex(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVEVARIABLEBORROWSCUMULATIVEINDEX, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Tuple4<Uint256, Uint256, Uint256, Bool>> getReserveConfiguration(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVECONFIGURATION, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple4<Uint256, Uint256, Uint256, Bool>>(
                new Callable<Tuple4<Uint256, Uint256, Uint256, Bool>>() {
                    @Override
                    public Tuple4<Uint256, Uint256, Uint256, Bool> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<Uint256, Uint256, Uint256, Bool>(
                                (Uint256) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2), 
                                (Bool) results.get(3));
                    }
                });
    }

    public RemoteCall<Uint256> getReserveDecimals(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVEDECIMALS, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> isReserveBorrowingEnabled(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISRESERVEBORROWINGENABLED, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> isReserveUsageAsCollateralEnabled(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISRESERVEUSAGEASCOLLATERALENABLED, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> getReserveIsStableBorrowRateEnabled(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVEISSTABLEBORROWRATEENABLED, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> getReserveIsActive(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVEISACTIVE, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> getReserveIsFreezed(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVEISFREEZED, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint40> getReserveLastUpdate(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVELASTUPDATE, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint40>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getReserveUtilizationRate(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVEUTILIZATIONRATE, 
                Arrays.<Type>asList(_reserve), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<DynamicArray<Address>> getReserves() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETRESERVES, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> isUserUseReserveAsCollateralEnabled(Address _reserve, Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISUSERUSERESERVEASCOLLATERALENABLED, 
                Arrays.<Type>asList(_reserve, _user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getUserOriginationFee(Address _reserve, Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERORIGINATIONFEE, 
                Arrays.<Type>asList(_reserve, _user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint8> getUserCurrentBorrowRateMode(Address _reserve, Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERCURRENTBORROWRATEMODE, 
                Arrays.<Type>asList(_reserve, _user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getUserCurrentStableBorrowRate(Address _reserve, Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERCURRENTSTABLEBORROWRATE, 
                Arrays.<Type>asList(_reserve, _user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Tuple3<Uint256, Uint256, Uint256>> getUserBorrowBalances(Address _reserve, Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERBORROWBALANCES, 
                Arrays.<Type>asList(_reserve, _user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<Uint256, Uint256, Uint256>>(
                new Callable<Tuple3<Uint256, Uint256, Uint256>>() {
                    @Override
                    public Tuple3<Uint256, Uint256, Uint256> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<Uint256, Uint256, Uint256>(
                                (Uint256) results.get(0), 
                                (Uint256) results.get(1), 
                                (Uint256) results.get(2));
                    }
                });
    }

    public RemoteCall<Uint256> getUserVariableBorrowCumulativeIndex(Address _reserve, Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERVARIABLEBORROWCUMULATIVEINDEX, 
                Arrays.<Type>asList(_reserve, _user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getUserLastUpdate(Address _reserve, Address _user) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETUSERLASTUPDATE, 
                Arrays.<Type>asList(_reserve, _user), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> refreshConfiguration() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REFRESHCONFIGURATION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> initReserve(Address _reserve, Address _aTokenAddress, Uint256 _decimals, Address _interestRateStrategyAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INITRESERVE, 
                Arrays.<Type>asList(_reserve, _aTokenAddress, _decimals, _interestRateStrategyAddress), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setReserveInterestRateStrategyAddress(Address _reserve, Address _rateStrategyAddress) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETRESERVEINTERESTRATESTRATEGYADDRESS, 
                Arrays.<Type>asList(_reserve, _rateStrategyAddress), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> enableBorrowingOnReserve(Address _reserve, Bool _stableBorrowRateEnabled) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ENABLEBORROWINGONRESERVE, 
                Arrays.<Type>asList(_reserve, _stableBorrowRateEnabled), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> disableBorrowingOnReserve(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DISABLEBORROWINGONRESERVE, 
                Arrays.<Type>asList(_reserve), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> enableReserveAsCollateral(Address _reserve, Uint256 _baseLTVasCollateral, Uint256 _liquidationThreshold, Uint256 _liquidationBonus) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ENABLERESERVEASCOLLATERAL, 
                Arrays.<Type>asList(_reserve, _baseLTVasCollateral, _liquidationThreshold, _liquidationBonus), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> disableReserveAsCollateral(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DISABLERESERVEASCOLLATERAL, 
                Arrays.<Type>asList(_reserve), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> enableReserveStableBorrowRate(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ENABLERESERVESTABLEBORROWRATE, 
                Arrays.<Type>asList(_reserve), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> disableReserveStableBorrowRate(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DISABLERESERVESTABLEBORROWRATE, 
                Arrays.<Type>asList(_reserve), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> activateReserve(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ACTIVATERESERVE, 
                Arrays.<Type>asList(_reserve), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> deactivateReserve(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DEACTIVATERESERVE, 
                Arrays.<Type>asList(_reserve), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> freezeReserve(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_FREEZERESERVE, 
                Arrays.<Type>asList(_reserve), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> unfreezeReserve(Address _reserve) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_UNFREEZERESERVE, 
                Arrays.<Type>asList(_reserve), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setReserveBaseLTVasCollateral(Address _reserve, Uint256 _ltv) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETRESERVEBASELTVASCOLLATERAL, 
                Arrays.<Type>asList(_reserve, _ltv), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setReserveLiquidationThreshold(Address _reserve, Uint256 _threshold) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETRESERVELIQUIDATIONTHRESHOLD, 
                Arrays.<Type>asList(_reserve, _threshold), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setReserveLiquidationBonus(Address _reserve, Uint256 _bonus) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETRESERVELIQUIDATIONBONUS, 
                Arrays.<Type>asList(_reserve, _bonus), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setReserveDecimals(Address _reserve, Uint256 _decimals) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETRESERVEDECIMALS, 
                Arrays.<Type>asList(_reserve, _decimals), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static LendingPoolCore load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new LendingPoolCore(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static LendingPoolCore load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LendingPoolCore(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static LendingPoolCore load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new LendingPoolCore(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static LendingPoolCore load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new LendingPoolCore(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class ReserveUpdatedEventResponse {
        public Log log;

        public Address reserve;

        public Uint256 liquidityRate;

        public Uint256 stableBorrowRate;

        public Uint256 variableBorrowRate;

        public Uint256 liquidityIndex;

        public Uint256 variableBorrowIndex;
    }
}
