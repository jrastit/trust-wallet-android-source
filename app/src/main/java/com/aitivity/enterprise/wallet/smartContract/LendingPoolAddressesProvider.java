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
import org.web3j.abi.datatypes.generated.Bytes32;
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
public class LendingPoolAddressesProvider extends Contract {
    private static final String BINARY = "Bin file was not provided";

    public static final String FUNC_GETADDRESS = "getAddress";

    public static final String FUNC_ISOWNER = "isOwner";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_GETLENDINGPOOL = "getLendingPool";

    public static final String FUNC_SETLENDINGPOOLIMPL = "setLendingPoolImpl";

    public static final String FUNC_GETLENDINGPOOLCORE = "getLendingPoolCore";

    public static final String FUNC_SETLENDINGPOOLCOREIMPL = "setLendingPoolCoreImpl";

    public static final String FUNC_GETLENDINGPOOLCONFIGURATOR = "getLendingPoolConfigurator";

    public static final String FUNC_SETLENDINGPOOLCONFIGURATORIMPL = "setLendingPoolConfiguratorImpl";

    public static final String FUNC_GETLENDINGPOOLDATAPROVIDER = "getLendingPoolDataProvider";

    public static final String FUNC_SETLENDINGPOOLDATAPROVIDERIMPL = "setLendingPoolDataProviderImpl";

    public static final String FUNC_GETLENDINGPOOLPARAMETERSPROVIDER = "getLendingPoolParametersProvider";

    public static final String FUNC_SETLENDINGPOOLPARAMETERSPROVIDERIMPL = "setLendingPoolParametersProviderImpl";

    public static final String FUNC_GETFEEPROVIDER = "getFeeProvider";

    public static final String FUNC_SETFEEPROVIDERIMPL = "setFeeProviderImpl";

    public static final String FUNC_GETLENDINGPOOLLIQUIDATIONMANAGER = "getLendingPoolLiquidationManager";

    public static final String FUNC_SETLENDINGPOOLLIQUIDATIONMANAGER = "setLendingPoolLiquidationManager";

    public static final String FUNC_GETLENDINGPOOLMANAGER = "getLendingPoolManager";

    public static final String FUNC_SETLENDINGPOOLMANAGER = "setLendingPoolManager";

    public static final String FUNC_GETPRICEORACLE = "getPriceOracle";

    public static final String FUNC_SETPRICEORACLE = "setPriceOracle";

    public static final String FUNC_GETLENDINGRATEORACLE = "getLendingRateOracle";

    public static final String FUNC_SETLENDINGRATEORACLE = "setLendingRateOracle";

    public static final String FUNC_GETTOKENDISTRIBUTOR = "getTokenDistributor";

    public static final String FUNC_SETTOKENDISTRIBUTOR = "setTokenDistributor";

    public static final Event ETHEREUMADDRESSUPDATED_EVENT = new Event("EthereumAddressUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event FEEPROVIDERUPDATED_EVENT = new Event("FeeProviderUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event LENDINGPOOLCONFIGURATORUPDATED_EVENT = new Event("LendingPoolConfiguratorUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event LENDINGPOOLCOREUPDATED_EVENT = new Event("LendingPoolCoreUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event LENDINGPOOLDATAPROVIDERUPDATED_EVENT = new Event("LendingPoolDataProviderUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event LENDINGPOOLLIQUIDATIONMANAGERUPDATED_EVENT = new Event("LendingPoolLiquidationManagerUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event LENDINGPOOLMANAGERUPDATED_EVENT = new Event("LendingPoolManagerUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event LENDINGPOOLPARAMETERSPROVIDERUPDATED_EVENT = new Event("LendingPoolParametersProviderUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event LENDINGPOOLUPDATED_EVENT = new Event("LendingPoolUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event LENDINGRATEORACLEUPDATED_EVENT = new Event("LendingRateOracleUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PRICEORACLEUPDATED_EVENT = new Event("PriceOracleUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event PROXYCREATED_EVENT = new Event("ProxyCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event TOKENDISTRIBUTORUPDATED_EVENT = new Event("TokenDistributorUpdated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected LendingPoolAddressesProvider(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected LendingPoolAddressesProvider(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected LendingPoolAddressesProvider(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected LendingPoolAddressesProvider(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<EthereumAddressUpdatedEventResponse> getEthereumAddressUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(ETHEREUMADDRESSUPDATED_EVENT, transactionReceipt);
        ArrayList<EthereumAddressUpdatedEventResponse> responses = new ArrayList<EthereumAddressUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            EthereumAddressUpdatedEventResponse typedResponse = new EthereumAddressUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<EthereumAddressUpdatedEventResponse> ethereumAddressUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, EthereumAddressUpdatedEventResponse>() {
            @Override
            public EthereumAddressUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(ETHEREUMADDRESSUPDATED_EVENT, log);
                EthereumAddressUpdatedEventResponse typedResponse = new EthereumAddressUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<EthereumAddressUpdatedEventResponse> ethereumAddressUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(ETHEREUMADDRESSUPDATED_EVENT));
        return ethereumAddressUpdatedEventFlowable(filter);
    }

    public List<FeeProviderUpdatedEventResponse> getFeeProviderUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FEEPROVIDERUPDATED_EVENT, transactionReceipt);
        ArrayList<FeeProviderUpdatedEventResponse> responses = new ArrayList<FeeProviderUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FeeProviderUpdatedEventResponse typedResponse = new FeeProviderUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FeeProviderUpdatedEventResponse> feeProviderUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, FeeProviderUpdatedEventResponse>() {
            @Override
            public FeeProviderUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FEEPROVIDERUPDATED_EVENT, log);
                FeeProviderUpdatedEventResponse typedResponse = new FeeProviderUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<FeeProviderUpdatedEventResponse> feeProviderUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FEEPROVIDERUPDATED_EVENT));
        return feeProviderUpdatedEventFlowable(filter);
    }

    public List<LendingPoolConfiguratorUpdatedEventResponse> getLendingPoolConfiguratorUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LENDINGPOOLCONFIGURATORUPDATED_EVENT, transactionReceipt);
        ArrayList<LendingPoolConfiguratorUpdatedEventResponse> responses = new ArrayList<LendingPoolConfiguratorUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LendingPoolConfiguratorUpdatedEventResponse typedResponse = new LendingPoolConfiguratorUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LendingPoolConfiguratorUpdatedEventResponse> lendingPoolConfiguratorUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LendingPoolConfiguratorUpdatedEventResponse>() {
            @Override
            public LendingPoolConfiguratorUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LENDINGPOOLCONFIGURATORUPDATED_EVENT, log);
                LendingPoolConfiguratorUpdatedEventResponse typedResponse = new LendingPoolConfiguratorUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<LendingPoolConfiguratorUpdatedEventResponse> lendingPoolConfiguratorUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LENDINGPOOLCONFIGURATORUPDATED_EVENT));
        return lendingPoolConfiguratorUpdatedEventFlowable(filter);
    }

    public List<LendingPoolCoreUpdatedEventResponse> getLendingPoolCoreUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LENDINGPOOLCOREUPDATED_EVENT, transactionReceipt);
        ArrayList<LendingPoolCoreUpdatedEventResponse> responses = new ArrayList<LendingPoolCoreUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LendingPoolCoreUpdatedEventResponse typedResponse = new LendingPoolCoreUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LendingPoolCoreUpdatedEventResponse> lendingPoolCoreUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LendingPoolCoreUpdatedEventResponse>() {
            @Override
            public LendingPoolCoreUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LENDINGPOOLCOREUPDATED_EVENT, log);
                LendingPoolCoreUpdatedEventResponse typedResponse = new LendingPoolCoreUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<LendingPoolCoreUpdatedEventResponse> lendingPoolCoreUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LENDINGPOOLCOREUPDATED_EVENT));
        return lendingPoolCoreUpdatedEventFlowable(filter);
    }

    public List<LendingPoolDataProviderUpdatedEventResponse> getLendingPoolDataProviderUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LENDINGPOOLDATAPROVIDERUPDATED_EVENT, transactionReceipt);
        ArrayList<LendingPoolDataProviderUpdatedEventResponse> responses = new ArrayList<LendingPoolDataProviderUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LendingPoolDataProviderUpdatedEventResponse typedResponse = new LendingPoolDataProviderUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LendingPoolDataProviderUpdatedEventResponse> lendingPoolDataProviderUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LendingPoolDataProviderUpdatedEventResponse>() {
            @Override
            public LendingPoolDataProviderUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LENDINGPOOLDATAPROVIDERUPDATED_EVENT, log);
                LendingPoolDataProviderUpdatedEventResponse typedResponse = new LendingPoolDataProviderUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<LendingPoolDataProviderUpdatedEventResponse> lendingPoolDataProviderUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LENDINGPOOLDATAPROVIDERUPDATED_EVENT));
        return lendingPoolDataProviderUpdatedEventFlowable(filter);
    }

    public List<LendingPoolLiquidationManagerUpdatedEventResponse> getLendingPoolLiquidationManagerUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LENDINGPOOLLIQUIDATIONMANAGERUPDATED_EVENT, transactionReceipt);
        ArrayList<LendingPoolLiquidationManagerUpdatedEventResponse> responses = new ArrayList<LendingPoolLiquidationManagerUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LendingPoolLiquidationManagerUpdatedEventResponse typedResponse = new LendingPoolLiquidationManagerUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LendingPoolLiquidationManagerUpdatedEventResponse> lendingPoolLiquidationManagerUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LendingPoolLiquidationManagerUpdatedEventResponse>() {
            @Override
            public LendingPoolLiquidationManagerUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LENDINGPOOLLIQUIDATIONMANAGERUPDATED_EVENT, log);
                LendingPoolLiquidationManagerUpdatedEventResponse typedResponse = new LendingPoolLiquidationManagerUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<LendingPoolLiquidationManagerUpdatedEventResponse> lendingPoolLiquidationManagerUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LENDINGPOOLLIQUIDATIONMANAGERUPDATED_EVENT));
        return lendingPoolLiquidationManagerUpdatedEventFlowable(filter);
    }

    public List<LendingPoolManagerUpdatedEventResponse> getLendingPoolManagerUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LENDINGPOOLMANAGERUPDATED_EVENT, transactionReceipt);
        ArrayList<LendingPoolManagerUpdatedEventResponse> responses = new ArrayList<LendingPoolManagerUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LendingPoolManagerUpdatedEventResponse typedResponse = new LendingPoolManagerUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LendingPoolManagerUpdatedEventResponse> lendingPoolManagerUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LendingPoolManagerUpdatedEventResponse>() {
            @Override
            public LendingPoolManagerUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LENDINGPOOLMANAGERUPDATED_EVENT, log);
                LendingPoolManagerUpdatedEventResponse typedResponse = new LendingPoolManagerUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<LendingPoolManagerUpdatedEventResponse> lendingPoolManagerUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LENDINGPOOLMANAGERUPDATED_EVENT));
        return lendingPoolManagerUpdatedEventFlowable(filter);
    }

    public List<LendingPoolParametersProviderUpdatedEventResponse> getLendingPoolParametersProviderUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LENDINGPOOLPARAMETERSPROVIDERUPDATED_EVENT, transactionReceipt);
        ArrayList<LendingPoolParametersProviderUpdatedEventResponse> responses = new ArrayList<LendingPoolParametersProviderUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LendingPoolParametersProviderUpdatedEventResponse typedResponse = new LendingPoolParametersProviderUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LendingPoolParametersProviderUpdatedEventResponse> lendingPoolParametersProviderUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LendingPoolParametersProviderUpdatedEventResponse>() {
            @Override
            public LendingPoolParametersProviderUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LENDINGPOOLPARAMETERSPROVIDERUPDATED_EVENT, log);
                LendingPoolParametersProviderUpdatedEventResponse typedResponse = new LendingPoolParametersProviderUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<LendingPoolParametersProviderUpdatedEventResponse> lendingPoolParametersProviderUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LENDINGPOOLPARAMETERSPROVIDERUPDATED_EVENT));
        return lendingPoolParametersProviderUpdatedEventFlowable(filter);
    }

    public List<LendingPoolUpdatedEventResponse> getLendingPoolUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LENDINGPOOLUPDATED_EVENT, transactionReceipt);
        ArrayList<LendingPoolUpdatedEventResponse> responses = new ArrayList<LendingPoolUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LendingPoolUpdatedEventResponse typedResponse = new LendingPoolUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LendingPoolUpdatedEventResponse> lendingPoolUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LendingPoolUpdatedEventResponse>() {
            @Override
            public LendingPoolUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LENDINGPOOLUPDATED_EVENT, log);
                LendingPoolUpdatedEventResponse typedResponse = new LendingPoolUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<LendingPoolUpdatedEventResponse> lendingPoolUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LENDINGPOOLUPDATED_EVENT));
        return lendingPoolUpdatedEventFlowable(filter);
    }

    public List<LendingRateOracleUpdatedEventResponse> getLendingRateOracleUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LENDINGRATEORACLEUPDATED_EVENT, transactionReceipt);
        ArrayList<LendingRateOracleUpdatedEventResponse> responses = new ArrayList<LendingRateOracleUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LendingRateOracleUpdatedEventResponse typedResponse = new LendingRateOracleUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<LendingRateOracleUpdatedEventResponse> lendingRateOracleUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, LendingRateOracleUpdatedEventResponse>() {
            @Override
            public LendingRateOracleUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(LENDINGRATEORACLEUPDATED_EVENT, log);
                LendingRateOracleUpdatedEventResponse typedResponse = new LendingRateOracleUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<LendingRateOracleUpdatedEventResponse> lendingRateOracleUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(LENDINGRATEORACLEUPDATED_EVENT));
        return lendingRateOracleUpdatedEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.newOwner = (Address) eventValues.getIndexedValues().get(1);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.newOwner = (Address) eventValues.getIndexedValues().get(1);
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<PriceOracleUpdatedEventResponse> getPriceOracleUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PRICEORACLEUPDATED_EVENT, transactionReceipt);
        ArrayList<PriceOracleUpdatedEventResponse> responses = new ArrayList<PriceOracleUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PriceOracleUpdatedEventResponse typedResponse = new PriceOracleUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PriceOracleUpdatedEventResponse> priceOracleUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PriceOracleUpdatedEventResponse>() {
            @Override
            public PriceOracleUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PRICEORACLEUPDATED_EVENT, log);
                PriceOracleUpdatedEventResponse typedResponse = new PriceOracleUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<PriceOracleUpdatedEventResponse> priceOracleUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PRICEORACLEUPDATED_EVENT));
        return priceOracleUpdatedEventFlowable(filter);
    }

    public List<ProxyCreatedEventResponse> getProxyCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(PROXYCREATED_EVENT, transactionReceipt);
        ArrayList<ProxyCreatedEventResponse> responses = new ArrayList<ProxyCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProxyCreatedEventResponse typedResponse = new ProxyCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            typedResponse.id = (Bytes32) eventValues.getNonIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ProxyCreatedEventResponse> proxyCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ProxyCreatedEventResponse>() {
            @Override
            public ProxyCreatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(PROXYCREATED_EVENT, log);
                ProxyCreatedEventResponse typedResponse = new ProxyCreatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                typedResponse.id = (Bytes32) eventValues.getNonIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<ProxyCreatedEventResponse> proxyCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PROXYCREATED_EVENT));
        return proxyCreatedEventFlowable(filter);
    }

    public List<TokenDistributorUpdatedEventResponse> getTokenDistributorUpdatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TOKENDISTRIBUTORUPDATED_EVENT, transactionReceipt);
        ArrayList<TokenDistributorUpdatedEventResponse> responses = new ArrayList<TokenDistributorUpdatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TokenDistributorUpdatedEventResponse typedResponse = new TokenDistributorUpdatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TokenDistributorUpdatedEventResponse> tokenDistributorUpdatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TokenDistributorUpdatedEventResponse>() {
            @Override
            public TokenDistributorUpdatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TOKENDISTRIBUTORUPDATED_EVENT, log);
                TokenDistributorUpdatedEventResponse typedResponse = new TokenDistributorUpdatedEventResponse();
                typedResponse.log = log;
                typedResponse.newAddress = (Address) eventValues.getIndexedValues().get(0);
                return typedResponse;
            }
        });
    }

    public Flowable<TokenDistributorUpdatedEventResponse> tokenDistributorUpdatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TOKENDISTRIBUTORUPDATED_EVENT));
        return tokenDistributorUpdatedEventFlowable(filter);
    }

    public RemoteCall<Address> getAddress(Bytes32 _key) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETADDRESS, 
                Arrays.<Type>asList(_key), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Bool> isOwner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_ISOWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<Address> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transferOwnership(Address newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(newOwner), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getLendingPool() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLENDINGPOOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setLendingPoolImpl(Address _pool) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETLENDINGPOOLIMPL, 
                Arrays.<Type>asList(_pool), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getLendingPoolCore() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLENDINGPOOLCORE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setLendingPoolCoreImpl(Address _lendingPoolCore) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETLENDINGPOOLCOREIMPL, 
                Arrays.<Type>asList(_lendingPoolCore), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getLendingPoolConfigurator() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLENDINGPOOLCONFIGURATOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setLendingPoolConfiguratorImpl(Address _configurator) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETLENDINGPOOLCONFIGURATORIMPL, 
                Arrays.<Type>asList(_configurator), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getLendingPoolDataProvider() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLENDINGPOOLDATAPROVIDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setLendingPoolDataProviderImpl(Address _provider) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETLENDINGPOOLDATAPROVIDERIMPL, 
                Arrays.<Type>asList(_provider), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getLendingPoolParametersProvider() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLENDINGPOOLPARAMETERSPROVIDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setLendingPoolParametersProviderImpl(Address _parametersProvider) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETLENDINGPOOLPARAMETERSPROVIDERIMPL, 
                Arrays.<Type>asList(_parametersProvider), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getFeeProvider() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETFEEPROVIDER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setFeeProviderImpl(Address _feeProvider) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETFEEPROVIDERIMPL, 
                Arrays.<Type>asList(_feeProvider), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getLendingPoolLiquidationManager() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLENDINGPOOLLIQUIDATIONMANAGER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setLendingPoolLiquidationManager(Address _manager) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETLENDINGPOOLLIQUIDATIONMANAGER, 
                Arrays.<Type>asList(_manager), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getLendingPoolManager() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLENDINGPOOLMANAGER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setLendingPoolManager(Address _lendingPoolManager) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETLENDINGPOOLMANAGER, 
                Arrays.<Type>asList(_lendingPoolManager), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getPriceOracle() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPRICEORACLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setPriceOracle(Address _priceOracle) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETPRICEORACLE, 
                Arrays.<Type>asList(_priceOracle), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getLendingRateOracle() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETLENDINGRATEORACLE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setLendingRateOracle(Address _lendingRateOracle) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETLENDINGRATEORACLE, 
                Arrays.<Type>asList(_lendingRateOracle), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Address> getTokenDistributor() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTOKENDISTRIBUTOR, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function);
    }

    public RemoteCall<TransactionReceipt> setTokenDistributor(Address _tokenDistributor) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETTOKENDISTRIBUTOR, 
                Arrays.<Type>asList(_tokenDistributor), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static LendingPoolAddressesProvider load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new LendingPoolAddressesProvider(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static LendingPoolAddressesProvider load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new LendingPoolAddressesProvider(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static LendingPoolAddressesProvider load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new LendingPoolAddressesProvider(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static LendingPoolAddressesProvider load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new LendingPoolAddressesProvider(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static class EthereumAddressUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }

    public static class FeeProviderUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }

    public static class LendingPoolConfiguratorUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }

    public static class LendingPoolCoreUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }

    public static class LendingPoolDataProviderUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }

    public static class LendingPoolLiquidationManagerUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }

    public static class LendingPoolManagerUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }

    public static class LendingPoolParametersProviderUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }

    public static class LendingPoolUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }

    public static class LendingRateOracleUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }

    public static class OwnershipTransferredEventResponse {
        public Log log;

        public Address previousOwner;

        public Address newOwner;
    }

    public static class PriceOracleUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }

    public static class ProxyCreatedEventResponse {
        public Log log;

        public Address newAddress;

        public Bytes32 id;
    }

    public static class TokenDistributorUpdatedEventResponse {
        public Log log;

        public Address newAddress;
    }
}
