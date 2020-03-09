package com.aitivity.enterprise.wallet.ui.widget;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aitivity.enterprise.wallet.entity.WawetCommand;
import com.aitivity.enterprise.wallet.viewmodel.WawetCommandDetailViewModel;
import com.aitivity.enterprise.wallet.viewmodel.WawetCommandDetailViewModelFactory;
import com.wallet.crypto.trustapp.R;
import com.wallet.crypto.trustapp.entity.NetworkInfo;
import com.wallet.crypto.trustapp.entity.Wallet;
import com.wallet.crypto.trustapp.ui.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

import static com.wallet.crypto.trustapp.C.Key.WAWETCOMMAND;

public class WawetCommandDetailActivity extends BaseActivity implements View.OnClickListener {

    @Inject
    WawetCommandDetailViewModelFactory wawetCommandDetailViewModelFactory;
    private WawetCommandDetailViewModel viewModel;

    private WawetCommand wawetCommand;
    //private TextView amount;

    private TextView resultText;
    private TextView fromText;
    private Button actionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidInjection.inject(this);



        setContentView(R.layout.activity_wawetcommand_detail);

        wawetCommand = getIntent().getParcelableExtra(WAWETCOMMAND);
        if (wawetCommand == null) {
            finish();
            return;
        }
        toolbar();

        //BigInteger gasFee = new BigInteger(wawetCommand.gasUsed).multiply(new BigInteger(wawetCommand.gasPrice));
        //amount = findViewById(R.id.amount);
        ((TextView) findViewById(R.id.id)).setText(new Long(wawetCommand.id).toString());
        ((TextView) findViewById(R.id.command)).setText(wawetCommand.command);
        resultText =  findViewById(R.id.result);
        actionButton = findViewById(R.id.action);
        fromText = findViewById(R.id.from);

        //((TextView) findViewById(R.id.from)).setText(new Long(wawetCommand.id).toString());
        /*
        ((TextView) findViewById(R.id.to)).setText(wawetCommand.to);
        ((TextView) findViewById(R.id.gas_fee)).setText(BalanceUtils.weiToEth(gasFee).toPlainString());
        ((TextView) findViewById(R.id.txn_hash)).setText(wawetCommand.hash);
        ((TextView) findViewById(R.id.txn_time)).setText(getDate(wawetCommand.timeStamp));
        ((TextView) findViewById(R.id.block_number)).setText(wawetCommand.blockNumber);
        */
        findViewById(R.id.action).setOnClickListener(this);


        viewModel = ViewModelProviders.of(this, wawetCommandDetailViewModelFactory)
                .get(WawetCommandDetailViewModel.class);
        viewModel.defaultNetwork().observe(this, this::onDefaultNetwork);
        viewModel.defaultWallet().observe(this, this::onDefaultWallet);
        viewModel.register().observe(this, this::onRegister);
        viewModel.deposit().observe(this, this::onDeposit);

        Wallet wallet = viewModel.defaultWallet().getValue();
        if (wallet != null)
            fromText.setText(wallet.address);


        try {
            JSONObject json = new JSONObject(wawetCommand.command);
            String action = json.getString("action");
            actionButton.setText(action);
            actionButton.setVisibility(View.VISIBLE);
        }catch (JSONException e){
            throw new RuntimeException(e.getMessage(), e);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        //setTitle(getString(R.string.unknown_balance_without_symbol));
        //setSubtitle("");
        //adapter.clear();
        //wawetCommandAdapter.clear();
        viewModel.prepare();
        //checkRoot();


        //balance.setText("DEFI -- ETH");

    }
    private void onDefaultNetwork(NetworkInfo networkInfo) {
        //TODO
    }

    private void onDefaultWallet(Wallet wallet) {
        fromText.setText(wallet.address);
    }

    /*
    private void onDefaultWallet(Wallet wallet) {
        boolean isSent = wawetCommand.from.toLowerCase().equals(wallet.address);
        String rawValue;
        String symbol;
        long decimals = 18;
        NetworkInfo networkInfo = viewModel.defaultNetwork().getValue();
        if (wawetCommand.operations == null || wawetCommand.operations.length == 0) {
            rawValue = wawetCommand.value;
            symbol = networkInfo == null ? "" : networkInfo.symbol;
        } else {
            rawValue = wawetCommand.operations[0].value;
            decimals = wawetCommand.operations[0].contract.decimals;
            symbol = wawetCommand.operations[0].contract.symbol;
        }

        amount.setTextColor(ContextCompat.getColor(this, isSent ? R.color.red : R.color.green));
        if (rawValue.equals("0")) {
            rawValue = "0 " + symbol;
        } else {
            rawValue = (isSent ? "-" : "+") + getScaledValue(rawValue, decimals) + " " + symbol;
        }
        amount.setText(rawValue);
    }

    private String getScaledValue(String valueStr, long decimals) {
        // Perform decimal conversion
        BigDecimal value = new BigDecimal(valueStr);
        value = value.divide(new BigDecimal(Math.pow(10, decimals)));
        int scale = 3 - value.precision() + value.scale();
        return value.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    private String getDate(long timeStampInSec) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(timeStampInSec * 1000);
        return DateFormat.getLongDateFormat(this).format(cal.getTime());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            viewModel.shareWawetCommandDetail(this, wawetCommand);
        }
        return super.onOptionsItemSelected(item);
    }

    private void onDefaultNetwork(NetworkInfo networkInfo) {
        findViewById(R.id.more_detail).setVisibility(
                TextUtils.isEmpty(networkInfo.etherscanUrl) ? View.GONE : View.VISIBLE);
    }
    */

    public void onRegister(String message){
        resultText.setText(message);
    }

    public void onDeposit(String message){
        resultText.setText(message);
    }

    @Override
    public void onClick(View v) {
        resultText.setText("Processing...");
        actionButton.setVisibility(View.INVISIBLE);
        try {
            JSONObject json = new JSONObject(wawetCommand.command);
            String action = json.getString("action");
            if (action.equals("register")){
                String starkKey = json.getString("starkKey");
                String starkSignature = json.getString("deFiSignature");
                viewModel.register(starkKey, starkSignature);
                return;
            }
            if (action.equals("deposit")){
                String tokenId = json.getString("starkTokenId");
                String vaultId = json.getString("vaultId");
                String amount = json.getString("value");
                viewModel.deposit(tokenId, vaultId, amount);
                return;
            }
            //viewModel.showMoreDetails(v.getContext(), wawetCommand);
        }catch (JSONException e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
     
}
