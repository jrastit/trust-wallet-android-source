package com.aitivity.enterprise.wallet.smartContract;

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
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.DynamicBytes;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
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
public class StarkEx extends Contract {
    private static final String BINARY = "Bin file was not provided";

    public static final String FUNC_FREEZE_GRACE_PERIOD = "FREEZE_GRACE_PERIOD";

    public static final String FUNC_DEPOSIT = "deposit";

    public static final String FUNC_REGISTERTOKENADMIN = "registerTokenAdmin";

    public static final String FUNC_GETORDERROOT = "getOrderRoot";

    public static final String FUNC_GETREGISTEREDAVAILABILITYVERIFIERS = "getRegisteredAvailabilityVerifiers";

    public static final String FUNC_ANNOUNCEAVAILABILITYVERIFIERREMOVALINTENT = "announceAvailabilityVerifierRemovalIntent";

    public static final String FUNC_FULLWITHDRAWALREQUEST = "fullWithdrawalRequest";

    public static final String FUNC_MAINACCEPTGOVERNANCE = "mainAcceptGovernance";

    public static final String FUNC_GETFULLWITHDRAWALREQUEST = "getFullWithdrawalRequest";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final String FUNC_ISVERIFIER = "isVerifier";

    public static final String FUNC_ISFROZEN = "isFrozen";

    public static final String FUNC_REGISTERVERIFIER = "registerVerifier";

    public static final String FUNC_ANNOUNCEVERIFIERREMOVALINTENT = "announceVerifierRemovalIntent";

    public static final String FUNC_GETSEQUENCENUMBER = "getSequenceNumber";

    public static final String FUNC_INITIALIZE = "initialize";

    public static final String FUNC_MAINISGOVERNOR = "mainIsGovernor";

    public static final String FUNC_DEPOSITRECLAIM = "depositReclaim";

    public static final String FUNC_GETREGISTEREDVERIFIERS = "getRegisteredVerifiers";

    public static final String FUNC_UPDATESTATE = "updateState";

    public static final String FUNC_GETVAULTROOT = "getVaultRoot";

    public static final String FUNC_MAINCANCELNOMINATION = "mainCancelNomination";

    public static final String FUNC_ISUSERADMIN = "isUserAdmin";

    public static final String FUNC_REGISTER = "register";

    public static final String FUNC_DEPOSIT_CANCEL_DELAY = "DEPOSIT_CANCEL_DELAY";

    public static final String FUNC_ADDNEWOPERATOR = "addNewOperator";

    public static final String FUNC_UNFREEZE = "unFreeze";

    public static final String FUNC_GETASSETDATA = "getAssetData";

    public static final String FUNC_GETORDERTREEHEIGHT = "getOrderTreeHeight";

    public static final String FUNC_MAINNOMINATENEWGOVERNOR = "mainNominateNewGovernor";

    public static final String FUNC_UNFREEZE_DELAY = "UNFREEZE_DELAY";

    public static final String FUNC_ESCAPE = "escape";

    public static final String FUNC_MAINREMOVEGOVERNOR = "mainRemoveGovernor";

    public static final String FUNC_UNREGISTERTOKENADMIN = "unregisterTokenAdmin";

    public static final String FUNC_GETDEPOSITBALANCE = "getDepositBalance";

    public static final String FUNC_REMOVEOPERATOR = "removeOperator";

    public static final String FUNC_UNREGISTERUSERADMIN = "unregisterUserAdmin";

    public static final String FUNC_REMOVEAVAILABILITYVERIFIER = "removeAvailabilityVerifier";

    public static final String FUNC_VERIFIER_REMOVAL_DELAY = "VERIFIER_REMOVAL_DELAY";

    public static final String FUNC_GETETHERKEY = "getEtherKey";

    public static final String FUNC_FREEZEREQUEST = "freezeRequest";

    public static final String FUNC_ISAVAILABILITYVERIFIER = "isAvailabilityVerifier";

    public static final String FUNC_REGISTERAVAILABILITYVERIFIER = "registerAvailabilityVerifier";

    public static final String FUNC_GETSTARKKEY = "getStarkKey";

    public static final String FUNC_MAIN_GOVERNANCE_INFO_TAG = "MAIN_GOVERNANCE_INFO_TAG";

    public static final String FUNC_DEPOSITCANCEL = "depositCancel";

    public static final String FUNC_REMOVEVERIFIER = "removeVerifier";

    public static final String FUNC_REGISTERTOKEN = "registerToken";

    public static final String FUNC_GETQUANTUM = "getQuantum";

    public static final String FUNC_MAX_VERIFIER_COUNT = "MAX_VERIFIER_COUNT";

    public static final String FUNC_GETWITHDRAWALBALANCE = "getWithdrawalBalance";

    public static final String FUNC_GETVAULTTREEHEIGHT = "getVaultTreeHeight";

    public static final String FUNC_REGISTERUSERADMIN = "registerUserAdmin";

    public static final String FUNC_VERSION = "VERSION";

    public static final Event LOGFULLWITHDRAWALREQUEST_EVENT = new Event("LogFullWithdrawalRequest", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOGWITHDRAWAL_EVENT = new Event("LogWithdrawal", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOGUSERWITHDRAWAL_EVENT = new Event("LogUserWithdrawal", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOGDEPOSIT_EVENT = new Event("LogDeposit", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOGDEPOSITCANCEL_EVENT = new Event("LogDepositCancel", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOGDEPOSITCANCELRECLAIMED_EVENT = new Event("LogDepositCancelReclaimed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOGROOTUPDATE_EVENT = new Event("LogRootUpdate", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOGUSERREGISTERED_EVENT = new Event("LogUserRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event LOGTOKENREGISTERED_EVENT = new Event("LogTokenRegistered", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}));
    ;

    public static final Event LOGFROZEN_EVENT = new Event("LogFrozen", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event LOGUNFROZEN_EVENT = new Event("LogUnFrozen", 
            Arrays.<TypeReference<?>>asList());
    ;

    public static final Event LOGNOMINATEDGOVERNOR_EVENT = new Event("LogNominatedGovernor", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event LOGNEWGOVERNORACCEPTED_EVENT = new Event("LogNewGovernorAccepted", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event LOGREMOVEDGOVERNOR_EVENT = new Event("LogRemovedGovernor", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
    ;

    public static final Event LOGNOMINATIONCANCELLED_EVENT = new Event("LogNominationCancelled", 
            Arrays.<TypeReference<?>>asList());
    ;

    @Deprecated
    protected StarkEx(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected StarkEx(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected StarkEx(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected StarkEx(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<Uint256> FREEZE_GRACE_PERIOD() {
        final Function function = new Function(FUNC_FREEZE_GRACE_PERIOD, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> deposit(Uint256 tokenId, Uint256 vaultId, Uint256 quantizedAmount) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(tokenId, vaultId, quantizedAmount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> registerTokenAdmin(Address newAdmin) {
        final Function function = new Function(
                FUNC_REGISTERTOKENADMIN, 
                Arrays.<Type>asList(newAdmin), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getOrderRoot() {
        final Function function = new Function(FUNC_GETORDERROOT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<DynamicArray<Address>> getRegisteredAvailabilityVerifiers() {
        final Function function = new Function(FUNC_GETREGISTEREDAVAILABILITYVERIFIERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> announceAvailabilityVerifierRemovalIntent(Address verifier) {
        final Function function = new Function(
                FUNC_ANNOUNCEAVAILABILITYVERIFIERREMOVALINTENT, 
                Arrays.<Type>asList(verifier), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> fullWithdrawalRequest(Uint256 vaultId) {
        final Function function = new Function(
                FUNC_FULLWITHDRAWALREQUEST, 
                Arrays.<Type>asList(vaultId), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> mainAcceptGovernance() {
        final Function function = new Function(
                FUNC_MAINACCEPTGOVERNANCE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getFullWithdrawalRequest(Uint256 starkKey, Uint256 vaultId) {
        final Function function = new Function(FUNC_GETFULLWITHDRAWALREQUEST, 
                Arrays.<Type>asList(starkKey, vaultId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> withdraw(Uint256 tokenId) {
        final Function function = new Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(tokenId), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Bool> isVerifier(Address verifierAddress) {
        final Function function = new Function(FUNC_ISVERIFIER, 
                Arrays.<Type>asList(verifierAddress), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> isFrozen() {
        final Function function = new Function(FUNC_ISFROZEN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> registerVerifier(Address verifier, Utf8String identifier) {
        final Function function = new Function(
                FUNC_REGISTERVERIFIER, 
                Arrays.<Type>asList(verifier, identifier), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> announceVerifierRemovalIntent(Address verifier) {
        final Function function = new Function(
                FUNC_ANNOUNCEVERIFIERREMOVALINTENT, 
                Arrays.<Type>asList(verifier), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getSequenceNumber() {
        final Function function = new Function(FUNC_GETSEQUENCENUMBER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> initialize(DynamicBytes data) {
        final Function function = new Function(
                FUNC_INITIALIZE, 
                Arrays.<Type>asList(data), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Bool> mainIsGovernor(Address testGovernor) {
        final Function function = new Function(FUNC_MAINISGOVERNOR, 
                Arrays.<Type>asList(testGovernor), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> depositReclaim(Uint256 tokenId, Uint256 vaultId) {
        final Function function = new Function(
                FUNC_DEPOSITRECLAIM, 
                Arrays.<Type>asList(tokenId, vaultId), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<DynamicArray<Address>> getRegisteredVerifiers() {
        final Function function = new Function(FUNC_GETREGISTEREDVERIFIERS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Address>>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> updateState(DynamicArray<Uint256> publicInput, DynamicArray<Uint256> applicationData) {
        final Function function = new Function(
                FUNC_UPDATESTATE, 
                Arrays.<Type>asList(publicInput, applicationData), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getVaultRoot() {
        final Function function = new Function(FUNC_GETVAULTROOT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> mainCancelNomination() {
        final Function function = new Function(
                FUNC_MAINCANCELNOMINATION, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Bool> isUserAdmin(Address testedAdmin) {
        final Function function = new Function(FUNC_ISUSERADMIN, 
                Arrays.<Type>asList(testedAdmin), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> register(Uint256 starkKey, DynamicBytes signature) {
        final Function function = new Function(
                FUNC_REGISTER, 
                Arrays.<Type>asList(starkKey, signature), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> DEPOSIT_CANCEL_DELAY() {
        final Function function = new Function(FUNC_DEPOSIT_CANCEL_DELAY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> addNewOperator(Address newOperator) {
        final Function function = new Function(
                FUNC_ADDNEWOPERATOR, 
                Arrays.<Type>asList(newOperator), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> unFreeze() {
        final Function function = new Function(
                FUNC_UNFREEZE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<DynamicBytes> getAssetData(Uint256 tokenId) {
        final Function function = new Function(FUNC_GETASSETDATA, 
                Arrays.<Type>asList(tokenId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getOrderTreeHeight() {
        final Function function = new Function(FUNC_GETORDERTREEHEIGHT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> mainNominateNewGovernor(Address newGovernor) {
        final Function function = new Function(
                FUNC_MAINNOMINATENEWGOVERNOR, 
                Arrays.<Type>asList(newGovernor), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> UNFREEZE_DELAY() {
        final Function function = new Function(FUNC_UNFREEZE_DELAY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> escape(Uint256 starkKey, Uint256 vaultId, Uint256 tokenId, Uint256 quantizedAmount) {
        final Function function = new Function(
                FUNC_ESCAPE, 
                Arrays.<Type>asList(starkKey, vaultId, tokenId, quantizedAmount), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> mainRemoveGovernor(Address governorForRemoval) {
        final Function function = new Function(
                FUNC_MAINREMOVEGOVERNOR, 
                Arrays.<Type>asList(governorForRemoval), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> unregisterTokenAdmin(Address oldAdmin) {
        final Function function = new Function(
                FUNC_UNREGISTERTOKENADMIN, 
                Arrays.<Type>asList(oldAdmin), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getDepositBalance(Uint256 starkKey, Uint256 tokenId, Uint256 vaultId) {
        final Function function = new Function(FUNC_GETDEPOSITBALANCE, 
                Arrays.<Type>asList(starkKey, tokenId, vaultId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> removeOperator(Address removedOperator) {
        final Function function = new Function(
                FUNC_REMOVEOPERATOR, 
                Arrays.<Type>asList(removedOperator), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> unregisterUserAdmin(Address oldAdmin) {
        final Function function = new Function(
                FUNC_UNREGISTERUSERADMIN, 
                Arrays.<Type>asList(oldAdmin), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeAvailabilityVerifier(Address verifier) {
        final Function function = new Function(
                FUNC_REMOVEAVAILABILITYVERIFIER, 
                Arrays.<Type>asList(verifier), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> VERIFIER_REMOVAL_DELAY() {
        final Function function = new Function(FUNC_VERIFIER_REMOVAL_DELAY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> getEtherKey(Uint256 starkKey) {
        final Function function = new Function(FUNC_GETETHERKEY, 
                Arrays.<Type>asList(starkKey), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> freezeRequest(Uint256 vaultId) {
        final Function function = new Function(
                FUNC_FREEZEREQUEST, 
                Arrays.<Type>asList(vaultId), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Bool> isAvailabilityVerifier(Address verifierAddress) {
        final Function function = new Function(FUNC_ISAVAILABILITYVERIFIER, 
                Arrays.<Type>asList(verifierAddress), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> registerAvailabilityVerifier(Address verifier, Utf8String identifier) {
        final Function function = new Function(
                FUNC_REGISTERAVAILABILITYVERIFIER, 
                Arrays.<Type>asList(verifier, identifier), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getStarkKey(Address etherKey) {
        final Function function = new Function(FUNC_GETSTARKKEY, 
                Arrays.<Type>asList(etherKey), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Utf8String> MAIN_GOVERNANCE_INFO_TAG() {
        final Function function = new Function(FUNC_MAIN_GOVERNANCE_INFO_TAG, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> depositCancel(Uint256 tokenId, Uint256 vaultId) {
        final Function function = new Function(
                FUNC_DEPOSITCANCEL, 
                Arrays.<Type>asList(tokenId, vaultId), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> removeVerifier(Address verifier) {
        final Function function = new Function(
                FUNC_REMOVEVERIFIER, 
                Arrays.<Type>asList(verifier), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> registerToken(Uint256 tokenId, DynamicBytes assetData, Uint256 quantum) {
        final Function function = new Function(
                FUNC_REGISTERTOKEN, 
                Arrays.<Type>asList(tokenId, assetData, quantum), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Uint256> getQuantum(Uint256 tokenId) {
        final Function function = new Function(FUNC_GETQUANTUM, 
                Arrays.<Type>asList(tokenId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> deposit(Uint256 tokenId, Uint256 vaultId, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_DEPOSIT, 
                Arrays.<Type>asList(tokenId, vaultId), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<Uint256> MAX_VERIFIER_COUNT() {
        final Function function = new Function(FUNC_MAX_VERIFIER_COUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getWithdrawalBalance(Uint256 starkKey, Uint256 tokenId) {
        final Function function = new Function(FUNC_GETWITHDRAWALBALANCE, 
                Arrays.<Type>asList(starkKey, tokenId), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Uint256> getVaultTreeHeight() {
        final Function function = new Function(FUNC_GETVAULTTREEHEIGHT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> registerUserAdmin(Address newAdmin) {
        final Function function = new Function(
                FUNC_REGISTERUSERADMIN, 
                Arrays.<Type>asList(newAdmin), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Utf8String> VERSION() {
        final Function function = new Function(FUNC_VERSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public List<LogFullWithdrawalRequestEventResponse> getLogFullWithdrawalRequestEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGFULLWITHDRAWALREQUEST_EVENT, transactionReceipt);
        ArrayList<LogFullWithdrawalRequestEventResponse> responses = new ArrayList<LogFullWithdrawalRequestEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogFullWithdrawalRequestEventResponse typedResponse = new LogFullWithdrawalRequestEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.vaultId = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogFullWithdrawalRequestEventResponse> logFullWithdrawalRequestEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogFullWithdrawalRequestEventResponse>() {
            @Override
            public LogFullWithdrawalRequestEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGFULLWITHDRAWALREQUEST_EVENT, log);
                LogFullWithdrawalRequestEventResponse typedResponse = new LogFullWithdrawalRequestEventResponse();
                typedResponse.log = log;
                typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.vaultId = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<LogFullWithdrawalRequestEventResponse> logFullWithdrawalRequestEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGFULLWITHDRAWALREQUEST_EVENT));
        return logFullWithdrawalRequestEventFlowable(filter);
    }

    public List<LogWithdrawalEventResponse> getLogWithdrawalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGWITHDRAWAL_EVENT, transactionReceipt);
        ArrayList<LogWithdrawalEventResponse> responses = new ArrayList<LogWithdrawalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogWithdrawalEventResponse typedResponse = new LogWithdrawalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.quantizedAmount = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogWithdrawalEventResponse> logWithdrawalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogWithdrawalEventResponse>() {
            @Override
            public LogWithdrawalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGWITHDRAWAL_EVENT, log);
                LogWithdrawalEventResponse typedResponse = new LogWithdrawalEventResponse();
                typedResponse.log = log;
                typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse.quantizedAmount = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<LogWithdrawalEventResponse> logWithdrawalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGWITHDRAWAL_EVENT));
        return logWithdrawalEventFlowable(filter);
    }

    public List<LogUserWithdrawalEventResponse> getLogUserWithdrawalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGUSERWITHDRAWAL_EVENT, transactionReceipt);
        ArrayList<LogUserWithdrawalEventResponse> responses = new ArrayList<LogUserWithdrawalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogUserWithdrawalEventResponse typedResponse = new LogUserWithdrawalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.quantizedAmount = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogUserWithdrawalEventResponse> logUserWithdrawalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogUserWithdrawalEventResponse>() {
            @Override
            public LogUserWithdrawalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGUSERWITHDRAWAL_EVENT, log);
                LogUserWithdrawalEventResponse typedResponse = new LogUserWithdrawalEventResponse();
                typedResponse.log = log;
                typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse.quantizedAmount = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<LogUserWithdrawalEventResponse> logUserWithdrawalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGUSERWITHDRAWAL_EVENT));
        return logUserWithdrawalEventFlowable(filter);
    }

    public List<LogDepositEventResponse> getLogDepositEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGDEPOSIT_EVENT, transactionReceipt);
        ArrayList<LogDepositEventResponse> responses = new ArrayList<LogDepositEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogDepositEventResponse typedResponse = new LogDepositEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.vaultId = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(2);
            typedResponse.quantizedAmount = (Uint256) eventValues.getNonIndexedValues().get(3);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogDepositEventResponse> logDepositEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogDepositEventResponse>() {
            @Override
            public LogDepositEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGDEPOSIT_EVENT, log);
                LogDepositEventResponse typedResponse = new LogDepositEventResponse();
                typedResponse.log = log;
                typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.vaultId = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(2);
                typedResponse.quantizedAmount = (Uint256) eventValues.getNonIndexedValues().get(3);
                return typedResponse;
            }
        });
    }

    public Flowable<LogDepositEventResponse> logDepositEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGDEPOSIT_EVENT));
        return logDepositEventFlowable(filter);
    }

    public List<LogDepositCancelEventResponse> getLogDepositCancelEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGDEPOSITCANCEL_EVENT, transactionReceipt);
        ArrayList<LogDepositCancelEventResponse> responses = new ArrayList<LogDepositCancelEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogDepositCancelEventResponse typedResponse = new LogDepositCancelEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.vaultId = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogDepositCancelEventResponse> logDepositCancelEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogDepositCancelEventResponse>() {
            @Override
            public LogDepositCancelEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGDEPOSITCANCEL_EVENT, log);
                LogDepositCancelEventResponse typedResponse = new LogDepositCancelEventResponse();
                typedResponse.log = log;
                typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.vaultId = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<LogDepositCancelEventResponse> logDepositCancelEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGDEPOSITCANCEL_EVENT));
        return logDepositCancelEventFlowable(filter);
    }

    public List<LogDepositCancelReclaimedEventResponse> getLogDepositCancelReclaimedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGDEPOSITCANCELRECLAIMED_EVENT, transactionReceipt);
        ArrayList<LogDepositCancelReclaimedEventResponse> responses = new ArrayList<LogDepositCancelReclaimedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogDepositCancelReclaimedEventResponse typedResponse = new LogDepositCancelReclaimedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.vaultId = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogDepositCancelReclaimedEventResponse> logDepositCancelReclaimedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogDepositCancelReclaimedEventResponse>() {
            @Override
            public LogDepositCancelReclaimedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGDEPOSITCANCELRECLAIMED_EVENT, log);
                LogDepositCancelReclaimedEventResponse typedResponse = new LogDepositCancelReclaimedEventResponse();
                typedResponse.log = log;
                typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.vaultId = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<LogDepositCancelReclaimedEventResponse> logDepositCancelReclaimedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGDEPOSITCANCELRECLAIMED_EVENT));
        return logDepositCancelReclaimedEventFlowable(filter);
    }

    public List<LogRootUpdateEventResponse> getLogRootUpdateEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGROOTUPDATE_EVENT, transactionReceipt);
        ArrayList<LogRootUpdateEventResponse> responses = new ArrayList<LogRootUpdateEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogRootUpdateEventResponse typedResponse = new LogRootUpdateEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sequenceNumber = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.vaultRoot = (Uint256) eventValues.getNonIndexedValues().get(1);
            typedResponse.orderRoot = (Uint256) eventValues.getNonIndexedValues().get(2);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogRootUpdateEventResponse> logRootUpdateEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogRootUpdateEventResponse>() {
            @Override
            public LogRootUpdateEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGROOTUPDATE_EVENT, log);
                LogRootUpdateEventResponse typedResponse = new LogRootUpdateEventResponse();
                typedResponse.log = log;
                typedResponse.sequenceNumber = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.vaultRoot = (Uint256) eventValues.getNonIndexedValues().get(1);
                typedResponse.orderRoot = (Uint256) eventValues.getNonIndexedValues().get(2);
                return typedResponse;
            }
        });
    }

    public Flowable<LogRootUpdateEventResponse> logRootUpdateEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGROOTUPDATE_EVENT));
        return logRootUpdateEventFlowable(filter);
    }

    public List<LogUserRegisteredEventResponse> getLogUserRegisteredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGUSERREGISTERED_EVENT, transactionReceipt);
        ArrayList<LogUserRegisteredEventResponse> responses = new ArrayList<LogUserRegisteredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogUserRegisteredEventResponse typedResponse = new LogUserRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.etherKey = (Address) eventValues.getNonIndexedValues().get(0);
            typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogUserRegisteredEventResponse> logUserRegisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogUserRegisteredEventResponse>() {
            @Override
            public LogUserRegisteredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGUSERREGISTERED_EVENT, log);
                LogUserRegisteredEventResponse typedResponse = new LogUserRegisteredEventResponse();
                typedResponse.log = log;
                typedResponse.etherKey = (Address) eventValues.getNonIndexedValues().get(0);
                typedResponse.starkKey = (Uint256) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<LogUserRegisteredEventResponse> logUserRegisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGUSERREGISTERED_EVENT));
        return logUserRegisteredEventFlowable(filter);
    }

    public List<LogTokenRegisteredEventResponse> getLogTokenRegisteredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGTOKENREGISTERED_EVENT, transactionReceipt);
        ArrayList<LogTokenRegisteredEventResponse> responses = new ArrayList<LogTokenRegisteredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogTokenRegisteredEventResponse typedResponse = new LogTokenRegisteredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(0);
            typedResponse.assetData = (DynamicBytes) eventValues.getNonIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogTokenRegisteredEventResponse> logTokenRegisteredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogTokenRegisteredEventResponse>() {
            @Override
            public LogTokenRegisteredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGTOKENREGISTERED_EVENT, log);
                LogTokenRegisteredEventResponse typedResponse = new LogTokenRegisteredEventResponse();
                typedResponse.log = log;
                typedResponse.tokenId = (Uint256) eventValues.getNonIndexedValues().get(0);
                typedResponse.assetData = (DynamicBytes) eventValues.getNonIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<LogTokenRegisteredEventResponse> logTokenRegisteredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGTOKENREGISTERED_EVENT));
        return logTokenRegisteredEventFlowable(filter);
    }

    public List<LogFrozenEventResponse> getLogFrozenEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGFROZEN_EVENT, transactionReceipt);
        ArrayList<LogFrozenEventResponse> responses = new ArrayList<LogFrozenEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogFrozenEventResponse typedResponse = new LogFrozenEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogFrozenEventResponse> logFrozenEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogFrozenEventResponse>() {
            @Override
            public LogFrozenEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGFROZEN_EVENT, log);
                LogFrozenEventResponse typedResponse = new LogFrozenEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<LogFrozenEventResponse> logFrozenEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGFROZEN_EVENT));
        return logFrozenEventFlowable(filter);
    }

    public List<LogUnFrozenEventResponse> getLogUnFrozenEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGUNFROZEN_EVENT, transactionReceipt);
        ArrayList<LogUnFrozenEventResponse> responses = new ArrayList<LogUnFrozenEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogUnFrozenEventResponse typedResponse = new LogUnFrozenEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogUnFrozenEventResponse> logUnFrozenEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogUnFrozenEventResponse>() {
            @Override
            public LogUnFrozenEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGUNFROZEN_EVENT, log);
                LogUnFrozenEventResponse typedResponse = new LogUnFrozenEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<LogUnFrozenEventResponse> logUnFrozenEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGUNFROZEN_EVENT));
        return logUnFrozenEventFlowable(filter);
    }

    public List<LogNominatedGovernorEventResponse> getLogNominatedGovernorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGNOMINATEDGOVERNOR_EVENT, transactionReceipt);
        ArrayList<LogNominatedGovernorEventResponse> responses = new ArrayList<LogNominatedGovernorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogNominatedGovernorEventResponse typedResponse = new LogNominatedGovernorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.nominatedGovernor = (Address) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogNominatedGovernorEventResponse> logNominatedGovernorEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogNominatedGovernorEventResponse>() {
            @Override
            public LogNominatedGovernorEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGNOMINATEDGOVERNOR_EVENT, log);
                LogNominatedGovernorEventResponse typedResponse = new LogNominatedGovernorEventResponse();
                typedResponse.log = log;
                typedResponse.nominatedGovernor = (Address) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<LogNominatedGovernorEventResponse> logNominatedGovernorEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGNOMINATEDGOVERNOR_EVENT));
        return logNominatedGovernorEventFlowable(filter);
    }

    public List<LogNewGovernorAcceptedEventResponse> getLogNewGovernorAcceptedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGNEWGOVERNORACCEPTED_EVENT, transactionReceipt);
        ArrayList<LogNewGovernorAcceptedEventResponse> responses = new ArrayList<LogNewGovernorAcceptedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogNewGovernorAcceptedEventResponse typedResponse = new LogNewGovernorAcceptedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.acceptedGovernor = (Address) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogNewGovernorAcceptedEventResponse> logNewGovernorAcceptedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogNewGovernorAcceptedEventResponse>() {
            @Override
            public LogNewGovernorAcceptedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGNEWGOVERNORACCEPTED_EVENT, log);
                LogNewGovernorAcceptedEventResponse typedResponse = new LogNewGovernorAcceptedEventResponse();
                typedResponse.log = log;
                typedResponse.acceptedGovernor = (Address) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<LogNewGovernorAcceptedEventResponse> logNewGovernorAcceptedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGNEWGOVERNORACCEPTED_EVENT));
        return logNewGovernorAcceptedEventFlowable(filter);
    }

    public List<LogRemovedGovernorEventResponse> getLogRemovedGovernorEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGREMOVEDGOVERNOR_EVENT, transactionReceipt);
        ArrayList<LogRemovedGovernorEventResponse> responses = new ArrayList<LogRemovedGovernorEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogRemovedGovernorEventResponse typedResponse = new LogRemovedGovernorEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.removedGovernor = (Address) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogRemovedGovernorEventResponse> logRemovedGovernorEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogRemovedGovernorEventResponse>() {
            @Override
            public LogRemovedGovernorEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGREMOVEDGOVERNOR_EVENT, log);
                LogRemovedGovernorEventResponse typedResponse = new LogRemovedGovernorEventResponse();
                typedResponse.log = log;
                typedResponse.removedGovernor = (Address) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<LogRemovedGovernorEventResponse> logRemovedGovernorEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGREMOVEDGOVERNOR_EVENT));
        return logRemovedGovernorEventFlowable(filter);
    }

    public List<LogNominationCancelledEventResponse> getLogNominationCancelledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGNOMINATIONCANCELLED_EVENT, transactionReceipt);
        ArrayList<LogNominationCancelledEventResponse> responses = new ArrayList<LogNominationCancelledEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogNominationCancelledEventResponse typedResponse = new LogNominationCancelledEventResponse();
            typedResponse.log = eventValues.getLog();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LogNominationCancelledEventResponse> logNominationCancelledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, LogNominationCancelledEventResponse>() {
            @Override
            public LogNominationCancelledEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LOGNOMINATIONCANCELLED_EVENT, log);
                LogNominationCancelledEventResponse typedResponse = new LogNominationCancelledEventResponse();
                typedResponse.log = log;
                return typedResponse;
            }
        });
    }

    public Flowable<LogNominationCancelledEventResponse> logNominationCancelledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LOGNOMINATIONCANCELLED_EVENT));
        return logNominationCancelledEventFlowable(filter);
    }

    @Deprecated
    public static StarkEx load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new StarkEx(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static StarkEx load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new StarkEx(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static StarkEx load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new StarkEx(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static StarkEx load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new StarkEx(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class LogFullWithdrawalRequestEventResponse {
        public Log log;

        public Uint256 starkKey;

        public Uint256 vaultId;
    }

    public static class LogWithdrawalEventResponse {
        public Log log;

        public Uint256 starkKey;

        public Uint256 tokenId;

        public Uint256 quantizedAmount;
    }

    public static class LogUserWithdrawalEventResponse {
        public Log log;

        public Uint256 starkKey;

        public Uint256 tokenId;

        public Uint256 quantizedAmount;
    }

    public static class LogDepositEventResponse {
        public Log log;

        public Uint256 starkKey;

        public Uint256 vaultId;

        public Uint256 tokenId;

        public Uint256 quantizedAmount;
    }

    public static class LogDepositCancelEventResponse {
        public Log log;

        public Uint256 starkKey;

        public Uint256 vaultId;

        public Uint256 tokenId;
    }

    public static class LogDepositCancelReclaimedEventResponse {
        public Log log;

        public Uint256 starkKey;

        public Uint256 vaultId;

        public Uint256 tokenId;
    }

    public static class LogRootUpdateEventResponse {
        public Log log;

        public Uint256 sequenceNumber;

        public Uint256 vaultRoot;

        public Uint256 orderRoot;
    }

    public static class LogUserRegisteredEventResponse {
        public Log log;

        public Address etherKey;

        public Uint256 starkKey;
    }

    public static class LogTokenRegisteredEventResponse {
        public Log log;

        public Uint256 tokenId;

        public DynamicBytes assetData;
    }

    public static class LogFrozenEventResponse {
        public Log log;
    }

    public static class LogUnFrozenEventResponse {
        public Log log;
    }

    public static class LogNominatedGovernorEventResponse {
        public Log log;

        public Address nominatedGovernor;
    }

    public static class LogNewGovernorAcceptedEventResponse {
        public Log log;

        public Address acceptedGovernor;
    }

    public static class LogRemovedGovernorEventResponse {
        public Log log;

        public Address removedGovernor;
    }

    public static class LogNominationCancelledEventResponse {
        public Log log;
    }
}
